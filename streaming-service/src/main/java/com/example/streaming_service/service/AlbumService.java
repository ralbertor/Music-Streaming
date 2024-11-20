package com.example.streaming_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.streaming_service.entidades.Album;
import com.example.streaming_service.repositorios.AlbumRepository;

import jakarta.transaction.Transactional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepo;

    public Album createAlbum(Album album) {
        if (album.getTitulo() == null || album.getAnoLanzamiento() == 0) {
            throw new IllegalArgumentException("titulo y año de lanzamiento obligatorios");
        }
        return albumRepo.save(album);
    }

    @Transactional
    public Album updateAlbum(int id, Album album) {
        if (albumRepo.existsById(id)) {
            Album nuevoAlbum = new Album();
            nuevoAlbum.setId(album.getId());
            nuevoAlbum.setTitulo(album.getTitulo());
            nuevoAlbum.setAnoLanzamiento(album.getAnoLanzamiento());
            nuevoAlbum.setDescripcion(album.getDescripcion());
            nuevoAlbum.setNumeroCanciones(album.getNumeroCanciones());
            nuevoAlbum.setUrlPortada(album.getUrlPortada());
            nuevoAlbum.setArtista(album.getArtista());

            if (nuevoAlbum.getCanciones() != null) {
                nuevoAlbum.getCanciones().clear();
                nuevoAlbum.getCanciones().addAll(album.getCanciones());
                album.getCanciones().forEach(cancion -> cancion.setAlbum(nuevoAlbum));
            }
            createAlbum(nuevoAlbum);
            return nuevoAlbum;
        } else {
            return null;
        }
    }

    @Transactional
    public void deleteAlbum(int id) {
        if (albumRepo.existsById(id)) {
            albumRepo.deleteById(id);
            System.out.println("200 álbum eliminado correctamente");
        } else {
             System.out.println("400, el álbum no existe");
        }
    }

    public Page<Album> buscarAlbumesPorFiltro(String filtro, Pageable pageable) {
        return albumRepo.buscarPorFiltro(filtro, pageable);
    }

}
