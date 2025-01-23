package com.example.streaming_service.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.streaming_service.DTO.Album.AlbumCancionDTO;
import com.example.streaming_service.DTO.Album.AlbumCreateDTO;
import com.example.streaming_service.DTO.Album.AlbumDTO;
import com.example.streaming_service.entidades.Album;
import com.example.streaming_service.service.AlbumService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/albumes")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private final ModelMapper modelMapper = new ModelMapper();

    private AlbumDTO convertToDTO(Album album){
        return modelMapper.map(album, AlbumDTO.class);
    }

    private Album convertToEntity(AlbumDTO albumDTO){
        return modelMapper.map(albumDTO, Album.class);
    }
    @Operation(summary="Crear un nuevo album", 
    description="Permite crear un álbum con los detalles proporcionados.")
    @PostMapping("/add")
    public ResponseEntity<Album> crearAlbum(@RequestBody AlbumCreateDTO albumDTO) {
        try {
            Album album = new Album();
            album.setTitulo(albumDTO.getTitulo());
            album.setAnoLanzamiento(albumDTO.getAnoLanzamiento());
            album.setDescripcion(albumDTO.getDescripcion());
            album.setNumeroCanciones(albumDTO.getNumeroCanciones());
            album.setUrlPortada(albumDTO.getUrlPortada());
            Album nuevoAlbum = albumService.createAlbum(album);
            return new ResponseEntity<>(nuevoAlbum, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

    }
    @Operation(summary="Crear un nuevo album con sus canciones", 
    description="Permite crear un album con los detalles proporcionados.")
    @PostMapping("/crearConCanciones")
    public ResponseEntity<Album> crearArtistaConAlbumYCanciones(
        @RequestBody AlbumCancionDTO albumDTO) {
            if(albumDTO == null){
                throw new IllegalArgumentException("AlbumDTO no puede ser nulo");
            }
        Album album = albumService.createAlbumWithCanciones(
            albumDTO.getAlbum(), albumDTO.getCanciones());
        
        return new ResponseEntity<>(album, HttpStatus.CREATED);
    }
    @Operation(summary="Actualizar un álbum", 
    description="Permite actualizar un álbum con los detalles proporcionados.")
    @PutMapping("/update/{id}")
    public ResponseEntity<AlbumDTO> actualizarAlbum(@PathVariable int id, @RequestBody AlbumDTO albumDTO) {
        Album album = convertToEntity(albumDTO);
        Album albumActualizado = albumService.updateAlbum(id, album);
        if (albumActualizado != null) {
            AlbumDTO albumActualizadoDTO = convertToDTO(albumActualizado);
            return ResponseEntity.ok(albumActualizadoDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
    @Operation(summary="Eliminar un álbum", 
    description="Permite eliminar un álbum.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarArtista(@PathVariable int id) {
        try {
            albumService.deleteAlbum(id);
            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @Operation(summary="Buscar album", 
    description="Permite buscar un álbum con el filtro proporcionados.")
    @GetMapping("/{id}")
    public ResponseEntity<Page<AlbumDTO>> buscarAlbum(
            @RequestParam(value = "filtro", required = false, defaultValue = "") String filtro,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Album> albumes = albumService.buscarAlbumesPorFiltro(filtro, pageable);
        Page<AlbumDTO> albumesDTO = albumes.map(this::convertToDTO);
        return ResponseEntity.ok(albumesDTO);
    }

    @Operation(summary= "Listar Albumes",
    description="Permite listar todos los albumes")
    @GetMapping("/todos")
    public ResponseEntity<List<AlbumDTO>> listarAlbumes() {
        List<Album> albumes = albumService.listarAlbumes();
        List<AlbumDTO> albumDTO = albumes.stream().
        map(this::convertToDTO)
        .collect(Collectors.toList());
        return ResponseEntity.ok().body(albumDTO);
    }
    

}
