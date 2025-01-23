package com.example.streaming_service.DTO.Artista;

import java.util.List;

import com.example.streaming_service.DTO.Album.AlbumCreateDTO;
import com.example.streaming_service.DTO.Cancion.CancionGeneroDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO para crear y actualizar Artistas con albumes y canciones")
public class ArtistaAlbumCancionDTO {
    @Schema(description="Artista que se va a crear")
    private ArtistaCreateDTO artista;
    @Schema(description = "Álbum que se va a crear del artista")
    private List<AlbumCreateDTO> albumes;
    @Schema(description="Canciones que se van a crear del artista")
    private List<CancionGeneroDTO> canciones;
    
}
