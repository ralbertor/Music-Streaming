package com.example.streaming_service.DTO.Cancion;

import java.sql.Time;
import java.util.List;

import lombok.Data;
@Data
public class CancionDTO {
    private String titulo;
    private Time duracion;
    private List<CancionArtistaDTO> artistas;
    private CancionAlbumDTO album;
    private List<CancionGeneroDTO> generos;

    
}
