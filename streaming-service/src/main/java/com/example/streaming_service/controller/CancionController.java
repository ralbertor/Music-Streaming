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

import com.example.streaming_service.DTO.Cancion.CancionCreateDTO;
import com.example.streaming_service.DTO.Cancion.CancionDTO;
import com.example.streaming_service.DTO.Cancion.CancionGeneroDTO;
import com.example.streaming_service.entidades.Cancion;
import com.example.streaming_service.service.CancionService;

import io.swagger.v3.oas.annotations.Operation;

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

    @Operation(summary="Crear una nueva canción con su genero", 
    description="Permite crear una canción con el genero.")
    @PostMapping("/crearConGeneros")
    public ResponseEntity<Cancion> crearCancionConGenero(@RequestBody CancionGeneroDTO cancionDTO) {
        Cancion cancion = cancionService.createCancionWithGeneros(cancionDTO);
        return new ResponseEntity<>(cancion, HttpStatus.CREATED);
    }
    
    @Operation(summary="Crear una nueva canción", 
    description="Permite crear una canción con los detalles proporcionados.")
    @PostMapping("/add")
    public ResponseEntity<Cancion> crearCancion(@RequestBody CancionCreateDTO cancionDTO) {
        try {
            Cancion nuevaCancion = cancionService.createCancion(cancionDTO);
            return new ResponseEntity<>(nuevaCancion, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(summary="Actualizar una canción", 
    description="Permite actualizar una canción.")
    @PutMapping("/update/{id}")
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
    @Operation(summary="Eliminar una canción", 
    description="Permite crear una canción")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarCancion(@PathVariable int id) {
        try {
            cancionService.deleteCancion(id);
            return ResponseEntity.noContent().build();

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @Operation(summary="Buscar una canción", 
    description="Permite buscar una canción con el filtro proporcionados.")
    @GetMapping("/{id}")
    public ResponseEntity<CancionDTO> obtenerArtistaPorId(@PathVariable int id) {
        Cancion cancion = cancionService.obtenerCancionPorId(id);
        if (cancion != null) {
            CancionDTO cancionDTO = convertToDTO(cancion);
            return ResponseEntity.ok(cancionDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @Operation(summary="Listar canciones",
    description="Permite listar todas las canciones")
    @GetMapping("/todos")
    public ResponseEntity<List<CancionDTO>> listarCanciones() {
        List<Cancion> canciones = cancionService.listarCanciones();
        List<CancionDTO> cancionDTO = canciones.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
        return ResponseEntity.ok().body(cancionDTO);
    }
}
