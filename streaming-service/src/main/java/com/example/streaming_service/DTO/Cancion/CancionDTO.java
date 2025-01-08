package com.example.streaming_service.DTO.Cancion;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Data
@Schema(description = "DTO para crear o actualizar una canción.")
public class CancionDTO {
    @Schema(description= "id de la canción", example = "2")
    private int id;
    @Schema(description = "Título de la canción", example="Mi canción favorita")
    private String titulo;
    @Schema(description="Duración de la canción en segundos", example = "210")
    private int duracion;
    @Schema(description="URL donde se puede escuchar la canción", example = "https://example.com/mi-cancion-favorita")
    private String urlCancion;
    
}
