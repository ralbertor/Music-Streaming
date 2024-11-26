package com.example.streaming_service.DTO.Artista;

import java.util.List;

import com.example.streaming_service.DTO.Album.AlbumDTO;
import com.example.streaming_service.DTO.Cancion.CancionDTO;

import lombok.Data;

@Data
public class ArtistaAlbumCancionDTO {

    private ArtistaDTO artista;
    private List<AlbumDTO> albumes;
    private List<CancionDTO> canciones;
    
}
