package com.example.streaming_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.streaming_service.DTO.Cancion.CancionDTO;
import com.example.streaming_service.entidades.Cancion;
import com.example.streaming_service.entidades.Genero;
import com.example.streaming_service.repositorios.CancionRepository;
import com.example.streaming_service.repositorios.GeneroRepository;

import jakarta.transaction.Transactional;

@Service
public class CancionService {

    @Autowired
    private CancionRepository cancionRepo;

    @Autowired
    private GeneroRepository generoRepo;
    
    public Cancion createCancionWithGeneros(CancionDTO cancionDTO){
        //Crear la cancion
        Cancion cancion = new Cancion();
        cancion.setTitulo(cancionDTO.getTitulo());
        cancion.setDuracion(cancionDTO.getDuracion());
        cancion.setUrlCancion(cancionDTO.getUrlCancion());

        //Obtener los géneros y asignarlos a la canción
        List<Genero> generos = new ArrayList<>();
        for (Integer generoid : cancionDTO.getGenerosIds()) {
            Genero genero = generoRepo.findById(generoid).orElseThrow(() -> 
            new RuntimeException("genero no encontrado"));
            generos.add(genero);
            
        }
        cancion.setGeneros(generos); //Asignar los géneros a la cancion
        
        //Guardar la canción con géneros asociados
        return cancionRepo.save(cancion);
    }


    public Cancion createCancion(Cancion cancion){
        if(cancion.getTitulo()==null || cancion.getDuracion()==0){
            throw new IllegalArgumentException("Título y duración obligatoria");
        }
        return cancionRepo.save(cancion);
    }
    @Transactional
    public Cancion updateCancion(int id, Cancion cancion){
        if(cancionRepo.existsById(id)){
            Cancion nuevaCancion = new Cancion();
            nuevaCancion.setId(cancion.getId());
            nuevaCancion.setTitulo(cancion.getTitulo());
            nuevaCancion.setDuracion(cancion.getDuracion());
            nuevaCancion.setUrlCancion(cancion.getUrlCancion());
            nuevaCancion.setAlbum(cancion.getAlbum());
            createCancion(nuevaCancion);
            return nuevaCancion;
        }
        else return null;
    }
    @Transactional
    public void deleteCancion(int id){
        if(cancionRepo.existsById(id)){
            cancionRepo.deleteById(id);
            System.out.println("200 canción eliminada correctamente");
        }
        else System.out.println("400, la canción no existe");
    }
    
    public Page<Cancion> buscarCancionPorFiltro(String filtro, Pageable pageable){
        return cancionRepo.buscarPorFiltro(filtro, pageable);
    }
    
}
