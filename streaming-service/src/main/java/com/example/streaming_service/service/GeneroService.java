package com.example.streaming_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
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
    public String updateGenero(int id, Genero genero) {
        if (generoRepo.existsById(id)) {
            Genero nuevoGenero = new Genero();
            nuevoGenero.setId(genero.getId());
            nuevoGenero.setNombre(genero.getNombre());
            nuevoGenero.setDescripcion(genero.getDescripcion());
            nuevoGenero.setAnoOrigen(genero.getAnoOrigen());
            createGenero(nuevoGenero);
            return "200 Género modificado correctamente";

        } else {
            return "400 Error al modificar el género";
        }
    }

    @Transactional
    public String removeGenero(int id) {
        if (generoRepo.existsById(id)) {
            generoRepo.deleteById(id);
            return "200 género eliminado correctamente";
        } else {
            return "400 el género no existe";
        }
    }

    public Page<Genero> buscarGenerosPorFiltro(String filtro, Pageable pageable) {
        return generoRepo.buscarPorFiltro(filtro, pageable);
    }

}
