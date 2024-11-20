package com.example.streaming_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.streaming_service.entidades.Artista;
import com.example.streaming_service.repositorios.ArtistaRepository;

import jakarta.transaction.Transactional;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepo;

    public Artista createArtista(Artista artista) {
        if (artista.getNombre() == null || artista.getNacionalidad() == null) {
            throw new IllegalArgumentException("Nombre y nacionalidad obligatorios");
        }
        return artistaRepo.save(artista);
    }

    @Transactional
    public Artista updateArtista(int id, Artista artista) {
        if (artistaRepo.existsById(id)) {
            Artista nuevoArtista = new Artista();
            nuevoArtista.setId(artista.getId());
            nuevoArtista.setNombre(artista.getNombre());
            nuevoArtista.setFechaNacimiento(artista.getFechaNacimiento());
            nuevoArtista.setNacionalidad(artista.getNacionalidad());
            if (nuevoArtista.getAlbumes() != null) {
                nuevoArtista.getAlbumes().clear();
                nuevoArtista.getAlbumes().addAll(artista.getAlbumes());
                artista.getAlbumes().forEach(album -> album.setArtista(nuevoArtista));
            }
            createArtista(nuevoArtista);
            return nuevoArtista;
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteArtista(int id) {
        if (artistaRepo.existsById(id)) {
            artistaRepo.deleteById(id);
            System.out.println("200 artista eliminado correctamente");
        } else {
            System.out.println("404, el artista no existe");
        }
    }

    public Page<Artista> buscarArtistasPorFiltro(String filtro, Pageable pageable) {
        return artistaRepo.buscarPorFiltro(filtro, pageable);
    }

}
