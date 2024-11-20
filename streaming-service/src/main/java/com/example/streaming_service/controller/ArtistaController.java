package com.example.streaming_service.controller;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.streaming_service.entidades.Artista;
import com.example.streaming_service.service.ArtistaService;


@RestController
@RequestMapping("/api/artistas")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @PostMapping
    public ResponseEntity<Artista> crearArtista(@RequestBody Artista artista) {
        try{
            Artista nuevoArtista = artistaService.createArtista(artista);
            return new ResponseEntity<>(nuevoArtista, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artista> actualizarArtista(@PathVariable int id, @RequestBody Artista artista) {
        Artista artistaActualizado = artistaService.updateArtista(id, artista);
        if(artistaActualizado !=null){
            return ResponseEntity.ok(artistaActualizado);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArtista(@PathVariable int id) {
       try {
            artistaService.deleteArtista(id);
            return ResponseEntity.noContent().build();       
       } catch (IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
       }
    
    }

    @GetMapping
    public ResponseEntity<Page<Artista>> buscarArtistas(
        @RequestParam(value = "filtro", required = false, defaultValue = "") String filtro,
    @RequestParam(value = "page", defaultValue = "0") int page,
    @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Artista> artistas = artistaService.buscarArtistasPorFiltro(filtro, pageable);
        return ResponseEntity.ok(artistas);
    }
    

}
