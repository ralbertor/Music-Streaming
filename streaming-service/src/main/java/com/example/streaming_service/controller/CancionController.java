package com.example.streaming_service.controller;

import org.modelmapper.ModelMapper;
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

import com.example.streaming_service.DTO.Cancion.CancionDTO;

@RestController
@RequestMapping("/api/canciones")
public class CancionController {

    @Autowired
    private CancionService cancionService;

    @Autowired
    private final ModelMapper modelMapper = new ModelMapper();

    private CancionDTO convertToDTO(Cancion cancion){
        return modelMapper.map(cancion, CancionDTO.class);
    }

    private Cancion convertToEntity(CancionDTO cancionDTO){
        return modelMapper.map(cancionDTO, Cancion.class);
    }

    @PostMapping("/crearConGeneros")
    public ResponseEntity<Cancion> crearCancionConGenero(@RequestBody CancionDTO cancionDTO) {
        Cancion cancion = cancionService.createCancionWithGeneros(cancionDTO);
        return new ResponseEntity<>(cancion, HttpStatus.CREATED);
    }
    

    @PostMapping("/canciones/add")
    public ResponseEntity<CancionDTO> crearCancion(@RequestBody CancionDTO cancionDTO) {
        try {
            Cancion cancion = convertToEntity(cancionDTO);
            Cancion nuevaCancion = cancionService.createCancion(cancion);
            CancionDTO nuevaCancionDTO = convertToDTO(nuevaCancion);
            return new ResponseEntity<>(nuevaCancionDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/canciones/update/{id}")
    public ResponseEntity<CancionDTO> actualizarCancion(@PathVariable int id, @RequestBody CancionDTO cancionDTO) {
        Cancion cancion = convertToEntity(cancionDTO);
        Cancion cancionActualizada = cancionService.updateCancion(id, cancion);
        if (cancionActualizada != null) {
            CancionDTO cancionActualizadaDTO = convertToDTO(cancionActualizada);
            return ResponseEntity.ok(cancionActualizadaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/canciones/delete/{id}")
    public ResponseEntity<Void> eliminarCancion(@PathVariable int id) {
        try {
            cancionService.deleteCancion(id);
            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/canciones/{id}")
    public ResponseEntity<Page<CancionDTO>> buscarCancion(@RequestParam(value = "filtro", required = false, defaultValue = "") String filtro,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Cancion> canciones = cancionService.buscarCancionPorFiltro(filtro, pageable);
        Page<CancionDTO> cancionesDTO = canciones.map(this::convertToDTO);
        return ResponseEntity.ok(cancionesDTO);
    }

}
