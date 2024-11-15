package com.example.streaming_service.entidades;

import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class Cancion {
    private int id;
    private String titulo;
    private Time duracion;
    private String urlCancion;

    @ManyToOne
    @JoinColumn(name = "albumId")
    private Album album;

   
   
}
