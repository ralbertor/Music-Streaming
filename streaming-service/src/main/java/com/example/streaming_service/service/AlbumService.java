package com.example.streaming_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.streaming_service.entidades.Album;
import com.example.streaming_service.repositorios.AlbumRepository;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepo;

    public List<Album> findAllAlbumes(){
        return albumRepo.findAll();
    }
    public Album findAlbumById(int id){
        return albumRepo.findById(id).orElse(null);
    }
    public Album saveAlbum(Album album){
        return albumRepo.save(album);
    }
    public String removeAlbum(int id){
        if(albumRepo.existsById(id)){
            albumRepo.deleteById(id);
            return "200 álbum eliminado correctamente";
        }
        else return "400, el álbum no existe";
    }
    public String removeAllAlbumes(){
        albumRepo.deleteAll();
        return "200 todos los albumes eliminados correctamente";
    }
}
