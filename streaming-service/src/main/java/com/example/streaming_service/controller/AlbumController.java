package com.example.streaming_service.controller;

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

@RestController
@RequestMapping("/api/albumes")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @PostMapping("/albumes/add")
    public ResponseEntity<Album> crearAlbum(@RequestBody Album album) {
        try {
            Album nuevoAlbum = albumService.createAlbum(album);
            return new ResponseEntity<>(nuevoAlbum, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PutMapping("/albumes/update/{id}")
    public ResponseEntity<Album> actualizarAlbum(@PathVariable int id, @RequestBody Album album) {
        Album albumActualizado = albumService.updateAlbum(id, album);
        if (albumActualizado != null) {
            return ResponseEntity.ok(albumActualizado);
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
    public ResponseEntity<Page<Album>> buscarAlbum(
            @RequestParam(value = "filtro", required = false, defaultValue = "") String filtro,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Album> albumes = albumService.buscarAlbumesPorFiltro(filtro, pageable);
        return ResponseEntity.ok(albumes);
    }

}
