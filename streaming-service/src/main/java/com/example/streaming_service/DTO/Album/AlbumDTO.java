package com.example.streaming_service.DTO.Album;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description="DTO para crear o actualizar un álbum")
public class AlbumDTO {
    @Schema(description= "id del album", example = "2")
    private int id;
    @Schema(description= "Título del álbum", example = "Canciones Perdidas")
    private String titulo;
    @Schema(description="Año Lanzamiento del álbum", example="1498")
    private int anoLanzamiento;
    @Schema(description="Descripción del álbum", example="son sueeeeñoooos")
    private String descripcion;
    @Schema(description="Número de canciones del álbum", example="30")
    private int numeroCanciones;
    @Schema(description="URL de la portada del álbum", example="www.sonsuenos.com")
    private String urlPortada;
    
}
