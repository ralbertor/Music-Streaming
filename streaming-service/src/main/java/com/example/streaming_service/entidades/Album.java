package com.example.streaming_service.entidades;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Entity
@Data
public class Album {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private int anoLanzamiento;
    private String descripcion;
    private int numeroCanciones;
    private String urlPortada;


    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;
    
    @OneToMany(mappedBy = "album")
    private List<Cancion> canciones;
    

}

