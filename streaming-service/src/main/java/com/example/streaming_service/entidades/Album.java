package com.example.streaming_service.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonBackReference //Relación inversa
    @JsonIgnore
    private Artista artista;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    @Schema(description= "Canciones del álbum")
    private List<Cancion> canciones;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoLanzamiento() {
        return anoLanzamiento;
    }

    public void setAnoLanzamiento(int anoLanzamiento) {
        this.anoLanzamiento = anoLanzamiento;
    }

    public int getNumeroCanciones() {
        return numeroCanciones;
    }

    public void setNumeroCanciones(int numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlPortada() {
        return this.urlPortada;
    }

    public void setUrlPortada(String urlPortada) {
        this.urlPortada = urlPortada;
    }

    public Artista getArtista() {
        return this.artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public List<Cancion> getCanciones() {
        return this.canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

}
