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

import com.example.streaming_service.DTO.Genero.GeneroDTO;
import com.example.streaming_service.entidades.Genero;
import com.example.streaming_service.service.GeneroService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @Autowired
    private final ModelMapper modelMapper = new ModelMapper();

    private GeneroDTO convertToDTO(Genero genero){
        return modelMapper.map(genero, GeneroDTO.class);

    }

    private Genero convertToEntity(GeneroDTO generoDTO){
        return modelMapper.map(generoDTO, Genero.class);
    }

    @Operation(summary="Crear un nuevo género", 
    description="Permite crear un género con los detalles proporcionados.")
    @PostMapping("/generos/add")
    public ResponseEntity<GeneroDTO> crearGenero(@RequestBody GeneroDTO generoDTO) {
        try {
            Genero genero = convertToEntity(generoDTO);
            Genero nuevoGenero = generoService.createGenero(genero);
            GeneroDTO nuevoGeneroDTO = convertToDTO(nuevoGenero);
            return new ResponseEntity<>(nuevoGeneroDTO, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @Operation(summary="Actualiza un género", 
    description="Permite actualizar un género.")
    @PutMapping("/generos/update/{id}")
    public ResponseEntity<GeneroDTO> actualizarGenero(@PathVariable int id, @RequestBody GeneroDTO generoDTO) {
        Genero genero = convertToEntity(generoDTO);
        Genero generoActualizado = generoService.updateGenero(id, genero);
        if (generoActualizado != null) {
            GeneroDTO generoActualizadoDTO = convertToDTO(generoActualizado);
            return ResponseEntity.ok(generoActualizadoDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @Operation(summary="Eliminar un género", 
    description="Permite eliminar un género.")
    @DeleteMapping("/generos/delete/{id}")
    public ResponseEntity<Void> eliminarGenero(@PathVariable int id) {
        try {
            generoService.deleteGenero(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }
    @Operation(summary="Buscar un género", 
    description="Permite buscar un género con los filtros proporcionados.")
    @GetMapping("/generos/{id}")
    public ResponseEntity<Page<GeneroDTO>> buscarGenero(@RequestParam(value = "filtro", required = false, defaultValue = "") String filtro,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Genero> generos = generoService.buscarGenerosPorFiltro(filtro, pageable);
        Page<GeneroDTO> generosDTO = generos.map(this::convertToDTO);
        return ResponseEntity.ok(generosDTO);
    }

}
