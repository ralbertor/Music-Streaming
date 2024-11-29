package com.example.streaming_service.DTO.Artista;

import java.util.List;

import com.example.streaming_service.DTO.Album.AlbumDTO;
import com.example.streaming_service.DTO.Cancion.CancionGeneroDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO para crear y actualizar Artistas con albumes y canciones")
public class ArtistaAlbumCancionDTO {
    @Schema(description="Artista que se va a crear")
    private ArtistaDTO artista;
    @Schema(description = "Álbum que se va a crear del artista")
    private List<AlbumDTO> albumes;
    @Schema(description="Canciones que se van a crear del artista")
    private List<CancionGeneroDTO> canciones;
    
}
