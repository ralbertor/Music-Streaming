package com.example.streaming_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.streaming_service.entidades.Genero;
import com.example.streaming_service.repositorios.GeneroRepository;

import jakarta.transaction.Transactional;

@Service
public class GeneroService {

    @Autowired
    private GeneroRepository generoRepo;

    public Genero createGenero(Genero genero) {
        if (genero.getNombre() == null || genero.getAnoOrigen() == 0) {
            throw new IllegalArgumentException("nombre y año de origen obligatorios");
        }
        return generoRepo.save(genero);
    }

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

    @Transactional
    public void deleteGenero(int id) {
        if (generoRepo.existsById(id)) {
            generoRepo.deleteById(id);
            System.out.println("200 género eliminado correctamente");
        } else {
            System.out.println("400 el género no existe");
        }
    }

    public Page<Genero> buscarGenerosPorFiltro(String filtro, Pageable pageable) {
        return generoRepo.buscarPorFiltro(filtro, pageable);
    }

}
