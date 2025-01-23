package com.example.streaming_service.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
@Schema(description = "Información de un artista")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Schema(description="Identificador del artista")
    private int id;

    @Schema(description = "Nombre del artista")
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Schema(description = "Fecha de nacimiento del artista")
    @Column(name = "FECHANACIMIENTO", nullable = false)
    private Date fechaNacimiento;

    @Schema(description="Nacionalidad del artista")
    @Column(name = "NACIONALIDAD", nullable = false)
    private String nacionalidad;

    @Schema(description="Albumes del artista")
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL)
    @JsonManagedReference //Relación principal
    private List<Album> albumes;

    @Schema(description = "Canciones del artista")
    @ManyToMany
    @JoinTable(
        name = "artista_cancion",
        joinColumns = @JoinColumn(name = "artista_id"),
        inverseJoinColumns = @JoinColumn(name = "cancion_id")
    )
    private List<Cancion> canciones = new ArrayList<>();


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {
        return this.nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public List<Album> getAlbumes() {
        return this.albumes;
    }

    public void setAlbumes(List<Album> albumes) {
        this.albumes = albumes;
    }

    public List<Cancion> getCanciones() {
        return this.canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }
}