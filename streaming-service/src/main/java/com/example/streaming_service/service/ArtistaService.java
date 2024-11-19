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

    public Artista crearArtista(Artista artista) {
        if (artista.getNombre() == null || artista.getNacionalidad() == null) {
            throw new IllegalArgumentException("Nombre y nacionalidad obligatorios");
        }
        return artistaRepo.save(artista);
    }

    @Transactional
    public String updateArtista(int id, Artista artista) {
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
            crearArtista(nuevoArtista);
            return "200, Artista modificado correctamente";
        } else {
            return "400, Error al modificar el artista";
        }
    }

    @Transactional
    public String deleteArtista(int id) {
        if (artistaRepo.existsById(id)) {
            artistaRepo.deleteById(id);
            return "200 artista eliminado correctamente";
        } else {
            return "404, el artista no existe";
        }
    }

    public Page<Artista> buscarArtistasPorFiltro(String filtro, Pageable pageable) {
        return artistaRepo.buscarPorFiltro(filtro, pageable);
    }

}
