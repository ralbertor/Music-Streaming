package com.example.streaming_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.streaming_service.service.CancionService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.streaming_service.entidades.Cancion;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/canciones")
public class CancionController {
    @Autowired
    private CancionService cancionService;

    @PostMapping
    public ResponseEntity<Cancion> crearCancion(@RequestBody Cancion cancion) {
        try {
            Cancion nuevaCancion = cancionService.createCancion(cancion);
            return new ResponseEntity<>(nuevaCancion, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cancion> actualizarCancion(@PathVariable int id, @RequestBody Cancion cancion) {
        Cancion cancionActualizada = cancionService.updateCancion(id, cancion);
        if(cancionActualizada != null){
            return ResponseEntity.ok(cancionActualizada);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCancion(@PathVariable int id){
        try {
            cancionService.deleteCancion(id);
            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping
    public ResponseEntity<Page<Cancion>> buscarCancion( @RequestParam(value = "filtro", required = false, defaultValue = "") String filtro,
    @RequestParam(value = "page", defaultValue = "0") int page,
    @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Cancion> canciones = cancionService.buscarCancionPorFiltro(filtro, pageable);
        return ResponseEntity.ok(canciones);
    }
    

}
