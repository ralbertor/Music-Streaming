package com.example.streaming_service.DTO.Cancion;

import java.util.List;

import com.example.streaming_service.entidades.Genero;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
@Schema(description = "DTO para crear o actualizar una canción.")
public class CancionGeneroDTO {
    @Schema(description = "Título de la canción", example="Mi canción favorita")
    private String titulo;
    @Schema(description="Duración de la canción en segundos", example = "210")
    private int duracion;
    @Schema(description="URL donde se puede escuchar la canción", example = "https://example.com/mi-cancion-favorita")
    private String urlCancion;
    @Schema(description="Lista de géneros asociados a la canción")
    private List<Genero> generos;
   
}
