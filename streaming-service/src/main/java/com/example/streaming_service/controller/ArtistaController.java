package com.example.streaming_service.controller;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.streaming_service.DTO.Album.AlbumDTO;
import com.example.streaming_service.DTO.Artista.ArtistaAlbumCancionDTO;
import com.example.streaming_service.DTO.Artista.ArtistaCreateDTO;
import com.example.streaming_service.DTO.Artista.ArtistaDTO;
import com.example.streaming_service.entidades.Artista;
import com.example.streaming_service.service.ArtistaService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;
    @Autowired
    private final ModelMapper modelMapper = new ModelMapper();

    private ArtistaDTO convertToDTO(Artista artista) {
        return modelMapper.map(artista, ArtistaDTO.class);
    }

    private Artista convertToEntity(ArtistaDTO artistaDTO) {
        return modelMapper.map(artistaDTO, Artista.class);
    }
    @Operation(summary="Crear un nuevo artista", 
    description="Permite crear un artista con los detalles proporcionados.")
    @PostMapping("/add")
    public ResponseEntity<Artista> crearArtista(@RequestBody ArtistaCreateDTO artistaDTO) {
        try {
            Artista artista = new Artista();
            artista.setNombre(artistaDTO.getNombre());
            artista.setFechaNacimiento(artistaDTO.getFechaNacimiento());
            artista.setNacionalidad(artistaDTO.getNacionalidad());
            Artista nuevoArtista = artistaService.createArtista(artista);
            return new ResponseEntity<>(nuevoArtista, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(summary="Crear un nuevo artista con su álbum y sus canciones", 
    description="Permite crear un artista con los detalles proporcionados.")
    @PostMapping("/crearConAlbumYCanciones")
    public ResponseEntity<Artista> crearArtistaConAlbumYCanciones(
        @RequestBody ArtistaAlbumCancionDTO artistaDTO) {
            if(artistaDTO == null){
                throw new IllegalArgumentException("ArtistaDTO no puede ser nulo");
            }
        Artista artista = artistaService.createArtistaWithAlbumCanciones(
            artistaDTO.getArtista(), artistaDTO.getAlbumes(), artistaDTO.getCanciones());
        
        return new ResponseEntity<>(artista, HttpStatus.CREATED);
    }
    @Operation(summary="Actualizar un artista", 
    description="Permite actualizar un artista.")
    @PutMapping("/update/{id}")
    public ResponseEntity<ArtistaDTO> actualizarArtista(@PathVariable int id, @RequestBody ArtistaDTO artistaDTO) {
        Artista artista = convertToEntity(artistaDTO);
        Artista artistaActualizado = artistaService.updateArtista(id, artista);
        if (artistaActualizado != null) {
            ArtistaDTO artistaActualizadoDTO = convertToDTO(artistaActualizado);
            return ResponseEntity.ok(artistaActualizadoDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @Operation(summary="Eliminar un artista", 
    description="Permite eliminar un artista.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarArtista(@PathVariable int id) {
        try {
            artistaService.deleteArtista(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @Operation(summary="Buscar un artista", 
    description="Permite buscar un artista con el filtro proporcionado.")
    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDTO> obtenerArtistaPorId(@PathVariable int id) {
        Artista artista = artistaService.obtenerArtistaPorId(id);
        if (artista != null) {
            ArtistaDTO artistaDTO = convertToDTO(artista);
            return ResponseEntity.ok(artistaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @Operation(summary="Listar Artistas",
    description="Permite listar todos los artistas")
    @GetMapping("/todos")
public ResponseEntity<List<ArtistaDTO>> listarArtistas() {
    List<Artista> artistas = artistaService.listarArtistas();
    List<ArtistaDTO> artistasDTO = artistas.stream().map(artista -> {
        ArtistaDTO dto = new ArtistaDTO();
        dto.setId(artista.getId());
        dto.setNombre(artista.getNombre());
        dto.setFechaNacimiento(artista.getFechaNacimiento());
        dto.setNacionalidad(artista.getNacionalidad());
        dto.setAlbumes(artista.getAlbumes().stream().map(album -> {
            AlbumDTO albumDTO = new AlbumDTO();
            albumDTO.setId(album.getId());
            albumDTO.setTitulo(album.getTitulo());
            albumDTO.setAnoLanzamiento(album.getAnoLanzamiento());
            albumDTO.setDescripcion(album.getDescripcion());
            albumDTO.setNumeroCanciones(album.getNumeroCanciones());
            albumDTO.setUrlPortada(album.getUrlPortada());
            return albumDTO;
        }).collect(Collectors.toList()));
        return dto;
    }).collect(Collectors.toList());
    return ResponseEntity.ok(artistasDTO);
}

       
}
