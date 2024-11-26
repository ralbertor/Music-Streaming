package com.example.streaming_service.controller;

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

import com.example.streaming_service.DTO.Artista.ArtistaAlbumCancionDTO;
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
    @PostMapping("/artistas/add")
    public ResponseEntity<ArtistaDTO> crearArtista(@RequestBody ArtistaDTO artistaDTO) {
        try {
            Artista artista = convertToEntity(artistaDTO);
            Artista nuevoArtista = artistaService.createArtista(artista);
            ArtistaDTO nuevoArtistaDTO = convertToDTO(nuevoArtista);
            return new ResponseEntity<>(nuevoArtistaDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(summary="Crear un nuevo artista con su álbum y sus canciones", 
    description="Permite crear un artista con los detalles proporcionados.")
    @PostMapping("/artistas/crearConAlbumYCanciones")
    public ResponseEntity<Artista> crearArtistaConAlbumYCanciones(
        @RequestBody ArtistaAlbumCancionDTO artistaDTO) {
        Artista artista = artistaService.createArtistaWithAlbumCanciones(artistaDTO.getArtista(), artistaDTO.getAlbumes(), artistaDTO.getCanciones());
        
        return new ResponseEntity<>(artista, HttpStatus.CREATED);
    }
    @Operation(summary="Actualizar un artista", 
    description="Permite actualizar un artista.")
    @PutMapping("/artistas/update/{id}")
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
    @DeleteMapping("/artistas/delete/{id}")
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
    @GetMapping("/artistas/{id}")
    public ResponseEntity<Page<ArtistaDTO>> buscarArtistas(
            @RequestParam(value = "filtro", required = false, defaultValue = "") String filtro,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Artista> artistas = artistaService.buscarArtistasPorFiltro(filtro, pageable);
        Page<ArtistaDTO> artistasDTO = artistas.map(this::convertToDTO);
        return ResponseEntity.ok(artistasDTO);
    }

}
