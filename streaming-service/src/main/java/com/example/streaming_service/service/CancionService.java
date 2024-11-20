package com.example.streaming_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.streaming_service.entidades.Cancion;
import com.example.streaming_service.repositorios.CancionRepository;

import jakarta.transaction.Transactional;

@Service
public class CancionService {

    @Autowired
    private CancionRepository cancionRepo;

    
    public Cancion createCancion(Cancion cancion){
        if(cancion.getTitulo()==null || cancion.getDuracion()==null){
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
