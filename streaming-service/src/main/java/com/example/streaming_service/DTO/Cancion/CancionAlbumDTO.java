package com.example.streaming_service.DTO.Cancion;

import lombok.Data;

@Data
public class CancionAlbumDTO {

    private int id;
    private String titulo;
    private int anoLanzamiento;
    private int numeroCanciones;
}
