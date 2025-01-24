package com.example.streaming_service.DTO.Artista;

import java.util.Date;
import java.util.List;

import com.example.streaming_service.DTO.Album.AlbumDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Información básica de un artista para crear o actualizar un artista.")
public class ArtistaDTO {

    @Schema(description = "id del artista", example = "2")
    private int id;
    @Schema(description = "nombre del artista", example = "Paco Pacheco")
    private String nombre;
    @Schema(description = "Fecha de nacimiento del artista", example = "2024-11-26")
    private Date fechaNacimiento;
    @Schema(description = "Nacionalidad del artista", example = "España")
    private String nacionalidad;

    private List<AlbumDTO> albumes;


    public ArtistaDTO() {
    }

    public ArtistaDTO(int id, String nombre, Date fechaNacimiento, String nacionalidad, List<AlbumDTO> albumes) {
        this.id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.albumes = albumes;
    }

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

    public List<AlbumDTO> getAlbumes() {
        return this.albumes;
    }

    public void setAlbumes(List<AlbumDTO> albumes) {
        this.albumes = albumes;
    }

    public ArtistaDTO id(int id) {
        setId(id);
        return this;
    }

    public ArtistaDTO nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public ArtistaDTO fechaNacimiento(Date fechaNacimiento) {
        setFechaNacimiento(fechaNacimiento);
        return this;
    }

    public ArtistaDTO nacionalidad(String nacionalidad) {
        setNacionalidad(nacionalidad);
        return this;
    }

    public ArtistaDTO albumes(List<AlbumDTO> albumes) {
        setAlbumes(albumes);
        return this;
    }

  

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", nacionalidad='" + getNacionalidad() + "'" +
            ", albumes='" + getAlbumes() + "'" +
            "}";
    }
    

}
