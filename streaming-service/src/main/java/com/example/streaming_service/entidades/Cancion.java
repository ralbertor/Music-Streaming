package com.example.streaming_service.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
    @JsonBackReference
    private Album album;

   @ManyToMany
    @JoinTable(
        name = "CANCION_GENERO",
        joinColumns = @JoinColumn(name = "cancion_id"),
        inverseJoinColumns = @JoinColumn(name = "genero_id")
    )
    @Schema(description="Generos de la canción")
    private List<Genero> generos;


    public Cancion(){}

    public Cancion( String titulo, int duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
       
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracion() {
        return this.duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getUrlCancion() {
        return this.urlCancion;
    }

    public void setUrlCancion(String urlCancion) {
        this.urlCancion = urlCancion;
    }

    public List<Artista> getArtistas() {
        return this.artistas;
    }

    public void setArtistas(List<Artista> artistas) {
        this.artistas = artistas;
    }

    public Album getAlbum() {
        return this.album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Genero> getGeneros() {
        return this.generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }
    
   
}
