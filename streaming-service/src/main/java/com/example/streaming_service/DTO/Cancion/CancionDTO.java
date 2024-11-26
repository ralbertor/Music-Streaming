package com.example.streaming_service.DTO.Cancion;

import java.util.List;

import lombok.Data;
@Data
public class CancionDTO {
    private String titulo;
    private int duracion;
    private String urlCancion;
    //private List<CancionArtistaDTO> artistas;
    //private CancionAlbumDTO album;
    private List<Integer> generosIds;

    
}
