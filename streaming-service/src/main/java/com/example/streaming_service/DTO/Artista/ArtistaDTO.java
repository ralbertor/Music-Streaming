package com.example.streaming_service.DTO.Artista;

import java.util.Date;
import java.util.List;

import com.example.streaming_service.DTO.Album.AlbumDTO;
import com.example.streaming_service.DTO.Cancion.CancionDTO;

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
    @Schema(description = "Lista de albumes")
    private List<AlbumDTO> albumes;

    @Schema(description = "Lista de canciones")
    private List<CancionDTO> canciones;


}
