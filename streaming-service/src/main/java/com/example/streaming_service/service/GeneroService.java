package com.example.streaming_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.streaming_service.entidades.Genero;
import com.example.streaming_service.repositorios.GeneroRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepo;

    @Operation(summary = "Crear un nuevo genero", 
            description = "Crea un genero con la información proporcionada",
            responses = {
                @ApiResponse(responseCode="201", description = "Cancion creada exitosamente"),
                @ApiResponse(responseCode= "400", description= "Datos inválidos")
            })
    public Genero createGenero(Genero genero) {
        if (genero.getNombre() == null || genero.getAnoOrigen() == 0) {
            throw new IllegalArgumentException("nombre y año de origen obligatorios");
        }
        return generoRepo.save(genero);
    }

    @Operation(summary = "Actualiza un genero", 
            description = "Actualiza un genero",
            responses = {
                @ApiResponse(responseCode="201", description = "Cancion actualizada exitosamente"),
                @ApiResponse(responseCode= "400", description= "Datos inválidos")
            })
    @Transactional
    public Genero updateGenero(int id, Genero genero) {
        if (generoRepo.existsById(id)) {
            Genero nuevoGenero = new Genero();
            nuevoGenero.setId(genero.getId());
            nuevoGenero.setNombre(genero.getNombre());
            nuevoGenero.setDescripcion(genero.getDescripcion());
            nuevoGenero.setAnoOrigen(genero.getAnoOrigen());
            createGenero(nuevoGenero);
            return nuevoGenero;

        } else {
            return null;
        }
    }
    @Operation(summary = "Elimina un genero", 
            description = "Elimina un genero",
            responses = {
                @ApiResponse(responseCode="200", description = "Cancion creada exitosamente"),
                @ApiResponse(responseCode= "400", description= "Datos inválidos")
            })
    @Transactional
    public void deleteGenero(int id) {
        if (generoRepo.existsById(id)) {
            generoRepo.deleteById(id);
            System.out.println("200 género eliminado correctamente");
        } else {
            System.out.println("400 el género no existe");
        }
    }
    @Operation(summary = "Buscar un genero", 
            description = "Busca un genero con el filtro dado",
            responses = {
                @ApiResponse(responseCode="200", description = "Género creado exitosamente"),
                @ApiResponse(responseCode= "400", description= "Datos inválidos")
            })
    public Page<Genero> buscarGenerosPorFiltro(String filtro, Pageable pageable) {
        return generoRepo.buscarPorFiltro(filtro, pageable);
    }
    @Operation(summary = "Lista los géneros",
        description="Lista todos los géneros",
        responses = {
                @ApiResponse(responseCode = "200", description = "Generos listados correctamente"),
                @ApiResponse(responseCode="400", description= "Datos inválidos")
        })
    public List<Genero> listarGeneros(){
        return generoRepo.findAll();
    }

}
