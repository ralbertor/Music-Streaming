package com.example.streaming_service.DTO.Album;

import java.sql.Time;

import lombok.Data;

@Data
public class AlbumCancionDTO {
    private int id;
    private String titulo;
    private Time duracion;
    
}
