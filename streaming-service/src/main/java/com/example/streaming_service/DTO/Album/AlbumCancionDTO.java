package com.example.streaming_service.DTO.Album;

import java.util.List;

import com.example.streaming_service.DTO.Cancion.CancionGeneroDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description="DTO para crear o actualizar un álbum")
public class AlbumCancionDTO {
      private AlbumCreateDTO album;
    private List<CancionGeneroDTO> canciones;
    
}
