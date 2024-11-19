package com.example.streaming_service.service;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.streaming_service.entidades.Cancion;
import com.example.streaming_service.repositorios.CancionRepository;

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
    public String updateCancion(Cancion cancion){
        int num = cancion.getId();
        if(cancionRepo.existsById(num)){
            Cancion nuevaCancion = new Cancion();
            nuevaCancion.setId(cancion.getId());
            nuevaCancion.setTitulo(cancion.getTitulo());
            nuevaCancion.setDuracion(cancion.getDuracion());
            nuevaCancion.setUrlCancion(cancion.getUrlCancion());
            nuevaCancion.setAlbum(cancion.getAlbum());
            createCancion(nuevaCancion);
            return "200 Canción modificada correctamente";
        }
        else return "400 Error al modificar la canción";
    }
    public String deleteCancion(int id){
        if(cancionRepo.existsById(id)){
            cancionRepo.deleteById(id);
            return "200 canción eliminada correctamente";
        }
        else return "400, la canción no existe";
    }
    
    public Page<Cancion> buscarCancionPorFiltro(String filtro, Pageable pageable){
        return cancionRepo.buscarPorFiltro(filtro, pageable);
    }
    
}
