package com.example.streaming_service.entidades;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ALBUM")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "ANOLANZAMIENTO")
    private int anoLanzamiento;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "NUMEROCANCIONES")
    private int numeroCanciones;
    @Column(name = "URLPORTADA")
    private String urlPortada;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    @OneToMany(mappedBy = "album")
    private List<Cancion> canciones;

}
