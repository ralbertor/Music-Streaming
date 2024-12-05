package com.example.streaming_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.streaming_service.DTO.Cancion.CancionDTO;
import com.example.streaming_service.DTO.Cancion.CancionGeneroDTO;
import com.example.streaming_service.entidades.Cancion;
import com.example.streaming_service.entidades.Genero;
import com.example.streaming_service.repositorios.CancionRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;

@Service
public class CancionService {

    @Autowired
    private CancionRepository cancionRepo;

    @Operation(summary = "Crear una nueva canción", 
        description = "Crea una canción con genero",
        responses = {
            @ApiResponse(responseCode="201", description = "Canción creada exitosamente"),
            @ApiResponse(responseCode= "400", description= "Datos inválidos")
        })
    public Cancion createCancionWithGeneros(CancionGeneroDTO cancionDTO) {
        //Crear la cancion
        Cancion cancion = new Cancion();
        cancion.setTitulo(cancionDTO.getTitulo());
        cancion.setDuracion(cancionDTO.getDuracion());
        cancion.setUrlCancion(cancionDTO.getUrlCancion());

        //Obtener los géneros y asignarlos a la canción
        List<Genero> generos = new ArrayList<>();
        cancion.setGeneros(generos); //Asignar los géneros a la cancion

        //Guardar la canción con géneros asociados
        return cancionRepo.save(cancion);
    }
    @Operation(summary = "Crear una nueva canción", 
        description = "Crea una canción con la información proporcionada",
        responses = {
            @ApiResponse(responseCode="201", description = "Canción creada exitosamente"),
            @ApiResponse(responseCode= "400", description= "Datos inválidos")
        })
    public Cancion createCancion(CancionDTO cancionDTO) {
       Cancion cancion = new Cancion();
       cancion.setTitulo(cancionDTO.getTitulo());
       cancion.setDuracion(cancionDTO.getDuracion());
       cancion.setUrlCancion(cancionDTO.getUrlCancion());

       cancion.setGeneros(new ArrayList<>());

       return cancionRepo.save(cancion);
    }

    @Operation(summary = "Actualizar una canción", 
        description = "Actualiza una canción",
        responses = {
            @ApiResponse(responseCode="201", description = "Cancion creada exitosamente"),
            @ApiResponse(responseCode= "400", description= "Datos inválidos")
        })
    @Transactional
    public Cancion updateCancion(int id, Cancion cancionActualizada) {
        return cancionRepo.findById(id).map(cancion -> {
            cancion.setTitulo(cancionActualizada.getTitulo());
            cancion.setDuracion(cancionActualizada.getDuracion());
            cancion.setUrlCancion(cancionActualizada.getUrlCancion());
            cancion.setAlbum(cancionActualizada.getAlbum());
            return cancionRepo.save(cancion);
        }).orElseThrow(() -> new RuntimeException("Cancion con ID" + id + " no encontrado"));
        } 
    @Operation(summary = "Elimina canción", 
        description = "Elimina una canción con la información proporcionada",
        responses = {
            @ApiResponse(responseCode="200", description = "Canción creada exitosamente"),
            @ApiResponse(responseCode= "400", description= "Datos inválidos")
        })
    @Transactional
    public void deleteCancion(int id) {
        if (cancionRepo.existsById(id)) {
            cancionRepo.deleteById(id);
            System.out.println("200 canción eliminada correctamente");
        } else {
            System.out.println("400, la canción no existe");
        }
    }
    @Operation(summary = "Buscar una canción", 
        description = "Busca una canción con el filtro dado",
        responses = {
            @ApiResponse(responseCode="200", description = "Canción encontrada"),
            @ApiResponse(responseCode= "400", description= "Datos inválidos")
        })
    public Page<Cancion> buscarCancionPorFiltro(String filtro, Pageable pageable) {
        return cancionRepo.buscarPorFiltro(filtro, pageable);
    }
    @Operation(summary = "Lista las canciones",
        description="Lista todas las cancioes",
        responses = {
                @ApiResponse(responseCode = "200", description = "Canciones listadas correctamente"),
                @ApiResponse(responseCode="400", description= "Datos inválidos")
        })
    public List<Cancion> listarCanciones(){
        return cancionRepo.findAll();
    }
}
