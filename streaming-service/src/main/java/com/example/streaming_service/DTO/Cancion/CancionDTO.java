package com.example.streaming_service.DTO.Cancion;

import java.util.List;

import com.example.streaming_service.entidades.Genero;

import lombok.Data;
@Data
public class CancionDTO {
    private String titulo;
    private int duracion;
    private String urlCancion;
    //private List<CancionArtistaDTO> artistas;
    //private CancionAlbumDTO album;
    private List<Genero> generos;

    
}
