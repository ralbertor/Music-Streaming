package com.example.streaming_service.DTO.Genero;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO para crear o actualizar un género")
public class GeneroCreateDTO {
    @Schema(description = "Nombre del género", example = "Salsa")
    private String nombre;
    @Schema(description = "Año Origen del género", example = "1942")
    private int anoOrigen;

    
}
