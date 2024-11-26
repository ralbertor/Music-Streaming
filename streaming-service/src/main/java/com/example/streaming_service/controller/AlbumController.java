package com.example.streaming_service.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.streaming_service.entidades.Album;
import com.example.streaming_service.service.AlbumService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.streaming_service.DTO.Album.AlbumDTO;

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

    @PostMapping("/albumes/add")
    public ResponseEntity<AlbumDTO> crearAlbum(@RequestBody AlbumDTO albumDTO) {
        try {
            Album album = convertToEntity(albumDTO);
            Album nuevoAlbum = albumService.createAlbum(album);
            AlbumDTO nuevoAlbumDTO = convertToDTO(nuevoAlbum);
            return new ResponseEntity<>(nuevoAlbumDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/albumes/update/{id}")
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

    @DeleteMapping("/albumes/delete/{id}")
    public ResponseEntity<Void> eliminarArtista(@PathVariable int id) {
        try {
            albumService.deleteAlbum(id);
            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/albumes/{id}")
    public ResponseEntity<Page<AlbumDTO>> buscarAlbum(
            @RequestParam(value = "filtro", required = false, defaultValue = "") String filtro,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Album> albumes = albumService.buscarAlbumesPorFiltro(filtro, pageable);
        Page<AlbumDTO> albumesDTO = albumes.map(this::convertToDTO);
        return ResponseEntity.ok(albumesDTO);
    }

}
