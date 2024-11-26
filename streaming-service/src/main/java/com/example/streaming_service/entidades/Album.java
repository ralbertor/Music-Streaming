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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "ALBUM")
@Schema(description="Información de un album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Schema(description="Identificador de un álbum")
    private int id;
    @Column(name = "TITULO")
    @Schema(description="Título del álbum")
    private String titulo;
    @Column(name = "ANOLANZAMIENTO")
    @Schema(description = "Año de lanzamiento del álbum")
    private int anoLanzamiento;
    @Column(name = "DESCRIPCION")
    @Schema(description= "Descripción del álbum")
    private String descripcion;
    @Column(name = "NUMEROCANCIONES")
    @Schema(description= "Número de canciones del álbum")
    private int numeroCanciones;
    @Column(name = "URLPORTADA")
    @Schema(description="URL de la portada del álbum")
    private String urlPortada;

    @ManyToOne
    @JoinColumn(name = "artistaId")
    @Schema(description="Artista del álbum")
    private Artista artista;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    @Schema(description= "Canciones del álbum")
    private List<Cancion> canciones;

}
