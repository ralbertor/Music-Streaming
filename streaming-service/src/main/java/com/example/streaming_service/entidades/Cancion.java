package com.example.streaming_service.entidades;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    private int duracion;
    @Column(name="URLCANCION")
    private String urlCancion;

    @ManyToMany(targetEntity = Artista.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Artista> artistas;

    @ManyToOne
    private Album album;

   @ManyToMany
    @JoinTable(
        name = "CANCION_GENERO",
        joinColumns = @JoinColumn(name = "cancion_id"),
        inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    private List<Genero> generos;
   
}
