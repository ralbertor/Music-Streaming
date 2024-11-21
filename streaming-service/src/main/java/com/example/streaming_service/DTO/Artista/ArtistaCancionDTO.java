package com.example.streaming_service.DTO.Artista;

import java.sql.Time;

import lombok.Data;
@Data
public class ArtistaCancionDTO {

    private int id;
    private String titulo;
    private Time duracion;
    
}
