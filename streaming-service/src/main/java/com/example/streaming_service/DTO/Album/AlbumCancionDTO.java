package com.example.streaming_service.DTO.Album;

import java.util.List;

import com.example.streaming_service.DTO.Cancion.CancionDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description="DTO para crear o actualizar un álbum")
public class AlbumCancionDTO {
    @Schema(description= "id del album", example = "2")
    private int id;
    @Schema(description= "Título del álbum", example = "Canciones Perdidas")
    private String titulo;
    @Schema(description="Año Lanzamiento del álbum", example="1498")
    private int anoLanzamiento;
    @Schema(description="Descripción del álbum", example="son sueeeeñoooos")
    private String descripcion;
    @Schema(description = "Album que se va a crear")
    private AlbumDTO album;
    @Schema(description="Canciones que se van a crear album")
    private List<CancionDTO> canciones;
    
}
