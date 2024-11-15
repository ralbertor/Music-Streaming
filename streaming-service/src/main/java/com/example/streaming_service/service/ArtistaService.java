package com.example.streaming_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.streaming_service.entidades.Artista;
import com.example.streaming_service.repositorios.ArtistaRepository;

@Service
public class ArtistaService {
    @Autowired
    private ArtistaRepository artistaRepo;
    public List<Artista> getAllArtistas(){
        return artistaRepo.findAll();
    }

    public Artista getArtistaById(int id){
        return artistaRepo.findById(id).orElse(null);

    }

    public Artista saveArtista(Artista artista){
        return artistaRepo.save(artista);
    }
    public String deleteArtista(int id){
        if(artistaRepo.existsById(id)){
            artistaRepo.deleteById(id);
            return "200 artista eliminado correctamente";
        }
        else return "404, el artista no existe";
    }
    public String deleteAllArtistas(){
        artistaRepo.deleteAll();
        return "200 todos los artistas eliminados correctamente";
    }
    public String updateArtista(Artista artista){
        int num = artista.getId();
        if(artistaRepo.existsById(num)){
            Artista nuevoArtista = new Artista();
            nuevoArtista.setId(artista.getId());
            nuevoArtista.setNombre(artista.getNombre());
            nuevoArtista.setFechaNacimiento(artista.getFechaNacimiento());
            nuevoArtista.setNacionalidad(artista.getNacionalidad());
            nuevoArtista.setAlbumes(artista.getAlbumes());
            artistaRepo.save(nuevoArtista);
            return "Artista modificado correctamente";
        }
        else return "Error al modificar el artista";
    }

}
