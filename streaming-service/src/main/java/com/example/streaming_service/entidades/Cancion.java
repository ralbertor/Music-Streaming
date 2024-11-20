package com.example.streaming_service.entidades;

import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Cancion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @Column(name="TITULO")
    private String titulo;
    @Column(name="DURACION")
    private Time duracion;
    @Column(name="URLCANCION")
    private String urlCancion;

    @ManyToOne
    @JoinColumn(name = "albumId")
    private Album album;

   
   
}
