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

}
