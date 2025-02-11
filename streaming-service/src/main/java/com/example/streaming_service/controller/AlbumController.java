package com.example.streaming_service.controller;

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
import com.example.streaming_service.DTO.Cancion.CancionDTO;
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
    @PostMapping("/CrearConCanciones")
    public ResponseEntity<Album> createAlbumWithCanciones(
            @RequestBody AlbumCancionDTO albumCreateRequest) {
        try {
            // Llamar al servicio para crear el álbum con canciones
            Album album = albumService.createAlbumWithCanciones(
                albumCreateRequest.getAlbum(),
                albumCreateRequest.getCanciones()
            );
    
            // Devolver el álbum creado
            return new ResponseEntity<>(album, HttpStatus.CREATED);
        } catch (Exception e) {
            // Manejo de errores
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    public ResponseEntity<AlbumDTO> obtenerArtistaPorId(@PathVariable int id) {
        Album album = albumService.obtenerArtistaPorId(id);
        if (album != null) {
            AlbumDTO albumDTO = convertToDTO(album);
            return ResponseEntity.ok(albumDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary= "Listar Albumes",
    description="Permite listar todos los albumes")
    @GetMapping("/todos")
    public ResponseEntity<Page<AlbumDTO>> listarAlbumes(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        Page<Album> albumes = albumService.listarAlbumes(pageable);
        Page<AlbumDTO> albumDTO = albumes.
        map(album ->{
            AlbumDTO dto = new AlbumDTO();
            dto.setId(album.getId());
            dto.setTitulo(album.getTitulo());
            dto.setAnoLanzamiento(album.getAnoLanzamiento());
            dto.setDescripcion(album.getDescripcion());
            dto.setNumeroCanciones(album.getNumeroCanciones());
            dto.setUrlPortada(album.getUrlPortada());
            dto.setCanciones(album.getCanciones().stream().map(cancion -> {
                CancionDTO cancionDTO = new CancionDTO();
                cancionDTO.setId(cancion.getId());
                cancionDTO.setTitulo(cancion.getTitulo());
                cancionDTO.setDuracion(cancion.getDuracion());
                cancionDTO.setUrlCancion(cancion.getUrlCancion());
                return cancionDTO;
            }).collect(Collectors.toList()));
            return dto;
        });
        return ResponseEntity.ok().body(albumDTO);
    }
    

}
