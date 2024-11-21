package com.example.streaming_service.DTO.Genero;

import java.sql.Time;

import lombok.Data;

@Data
public class GeneroCancionDTO {

    private int id;
    private String titulo;
    private Time duracion;
    
}
