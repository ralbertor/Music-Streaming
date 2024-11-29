package com.example.streaming_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.streaming_service.entidades.Album;
import com.example.streaming_service.repositorios.AlbumRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepo;

    @Operation(summary = "Crear un nuevo álbum", 
        description = "Crea un álbum con la información proporcionada",
        responses = {
            @ApiResponse(responseCode="201", description = "Álbum creado exitosamente"),
            @ApiResponse(responseCode= "400", description= "Datos inválidos")
        })
    public Album createAlbum(Album album) {
        if (album.getTitulo() == null || album.getAnoLanzamiento() == 0) {
            throw new IllegalArgumentException("titulo y año de lanzamiento obligatorios");
        }
        return albumRepo.save(album);
    }
    @Operation(summary = "Actualizar un álbum", 
        description = "Actualiza un álbum",
        responses = {
            @ApiResponse(responseCode="201", description = "Álbum actualizado exitosamente"),
            @ApiResponse(responseCode= "400", description= "Datos inválidos")
        })
    @Transactional
    public Album updateAlbum(int id, Album album) {
        if (albumRepo.existsById(id)) {
            Album nuevoAlbum = new Album();
            nuevoAlbum.setId(album.getId());
            nuevoAlbum.setTitulo(album.getTitulo());
            nuevoAlbum.setAnoLanzamiento(album.getAnoLanzamiento());
            nuevoAlbum.setDescripcion(album.getDescripcion());
            nuevoAlbum.setNumeroCanciones(album.getNumeroCanciones());
            nuevoAlbum.setUrlPortada(album.getUrlPortada());
            nuevoAlbum.setArtista(album.getArtista());

            if (nuevoAlbum.getCanciones() != null) {
                nuevoAlbum.getCanciones().clear();
                nuevoAlbum.getCanciones().addAll(album.getCanciones());
                album.getCanciones().forEach(cancion -> cancion.setAlbum(nuevoAlbum));
            }
            createAlbum(nuevoAlbum);
            return nuevoAlbum;
        } else {
            return null;
        }
    }
    @Operation(summary = "Eliminar un álbum", 
        description = "Elimina un álbum",
        responses = {
            @ApiResponse(responseCode="200", description = "Álbum eliminado exitosamente"),
            @ApiResponse(responseCode= "400", description= "Datos inválidos")
        })
    @Transactional
    public void deleteAlbum(int id) {
        if (albumRepo.existsById(id)) {
            albumRepo.deleteById(id);
            System.out.println("200 álbum eliminado correctamente");
        } else {
             System.out.println("400, el álbum no existe");
        }
    }
    @Operation(summary = "Buscar un álbum", 
        description = "Busca un álbum con el filtro dado",
        responses = {
            @ApiResponse(responseCode="200", description = "Álbum encontrado"),
            @ApiResponse(responseCode= "400", description= "Datos inválidos")
        })
    public Page<Album> buscarAlbumesPorFiltro(String filtro, Pageable pageable) {
        return albumRepo.buscarPorFiltro(filtro, pageable);
    }
    @Operation(summary= "Listar un álbum",
        description="Listar todos los albumes",
        responses={
            @ApiResponse(responseCode = "200", description="Todos los albumes listados"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
        })
    public List<Album> listarAlbumes(){
        return albumRepo.findAll();
    }

}
