package com.example.streaming_service.entidades;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description="Informacion de las canciones")
public class Cancion {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    @Schema(description="Identificador de la canción")
    private int id;
    @Column(name="TITULO")
    @Schema(description="Título de la canción")
    private String titulo;
    @Column(name="DURACION")
    @Schema(description="Duración de la canción")
    private int duracion;
    @Column(name="URLCANCION")
    @Schema(description="URL de la canción")
    private String urlCancion;

    @ManyToMany(targetEntity = Artista.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Schema(description="Artistas pertenecientes a la canción")
    private List<Artista> artistas;

    @ManyToOne
    @Schema(description="Álbum al que pertenece la canción")
    private Album album;

   @ManyToMany
    @JoinTable(
        name = "CANCION_GENERO",
        joinColumns = @JoinColumn(name = "cancion_id"),
        inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    @Schema(description="Generos de la canción")
    private List<Genero> generos;
   
}
