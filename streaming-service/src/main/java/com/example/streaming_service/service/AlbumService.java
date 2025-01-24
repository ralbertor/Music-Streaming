package com.example.streaming_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.streaming_service.DTO.Album.AlbumCreateDTO;
import com.example.streaming_service.DTO.Cancion.CancionGeneroDTO;
import com.example.streaming_service.DTO.Genero.GeneroCreateDTO;
import com.example.streaming_service.entidades.Album;
import com.example.streaming_service.entidades.Cancion;
import com.example.streaming_service.entidades.Genero;
import com.example.streaming_service.repositorios.AlbumRepository;
import com.example.streaming_service.repositorios.CancionRepository;
import com.example.streaming_service.repositorios.GeneroRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;

@Service
public class AlbumService {

    @Autowired
    private AlbumRepository albumRepo;

    @Autowired
    private CancionRepository cancionRepo;

    @Autowired
    private GeneroRepository generoRepo;

    @Operation(summary = "Crear un nuevo álbum con canciones",
            description = "Crea un álbum con las canciones proporcionadas",
            responses = {
                @ApiResponse(responseCode = "201", description = "Álbum creado exitosamente"),
                @ApiResponse(responseCode = "400", description = "Datos inválidos")
            })
    public Album createAlbumWithCanciones(AlbumCreateDTO albumDTO,
        List<CancionGeneroDTO> cancionDTOs) {
    // Crear álbum
    Album album = new Album();
    album.setTitulo(albumDTO.getTitulo());
    album.setAnoLanzamiento(albumDTO.getAnoLanzamiento());
    album.setDescripcion(albumDTO.getDescripcion());
    album.setNumeroCanciones(albumDTO.getNumeroCanciones());
    album.setUrlPortada(albumDTO.getUrlPortada());

    // Guardar álbum
    album = albumRepo.save(album);

    // Crear canciones y asignarlas al álbum
    List<Cancion> canciones = new ArrayList<>();
    for (CancionGeneroDTO cancionDTO : cancionDTOs) {
        Cancion cancion = new Cancion();
        cancion.setTitulo(cancionDTO.getTitulo());
        cancion.setDuracion(cancionDTO.getDuracion());
        cancion.setUrlCancion(cancionDTO.getUrlCancion());
        cancion.setAlbum(album);

        // Crear géneros y asignarlos a la canción
        List<Genero> generos = new ArrayList<>();
        for (GeneroCreateDTO generoDTO : cancionDTO.getGeneros()) {
            Genero genero = new Genero();
            genero.setNombre(generoDTO.getNombre());
            genero.setAnoOrigen(generoDTO.getAnoOrigen());

            // Guardar género
            genero = generoRepo.save(genero);
            generos.add(genero);
        }
        cancion.setGeneros(generos); // Asignar géneros a la canción

        // Guardar canción
        cancion = cancionRepo.save(cancion);
        canciones.add(cancion);
    }

    // Relacionar canciones con el álbum
    album.setCanciones(canciones);

    // Actualizar el número de canciones basado en la lista creada
    album.setNumeroCanciones(canciones.size());

    // Guardar y devolver el álbum actualizado
    return albumRepo.save(album);
}


    @Operation(summary = "Crear un nuevo álbum",
            description = "Crea un álbum con la información proporcionada",
            responses = {
                @ApiResponse(responseCode = "201", description = "Álbum creado exitosamente"),
                @ApiResponse(responseCode = "400", description = "Datos inválidos")
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
                @ApiResponse(responseCode = "201", description = "Álbum actualizado exitosamente"),
                @ApiResponse(responseCode = "400", description = "Datos inválidos")
            })
    @Transactional
    public Album updateAlbum(int id, Album albumActualizado) {
        return albumRepo.findById(id).map(album -> {
            album.setTitulo(albumActualizado.getTitulo());
            album.setAnoLanzamiento(albumActualizado.getAnoLanzamiento());
            album.setDescripcion(albumActualizado.getDescripcion());
            album.setNumeroCanciones(albumActualizado.getNumeroCanciones());
            album.setUrlPortada(albumActualizado.getUrlPortada());
            album.setArtista(albumActualizado.getArtista());

            if (albumActualizado.getCanciones() != null) {
                album.getCanciones().clear();
                album.getCanciones().addAll(albumActualizado.getCanciones());
                album.getCanciones().forEach(cancion -> cancion.setAlbum(album));
            }
            return albumRepo.save(album);
        }).orElseThrow(() -> new RuntimeException("Album con ID" + id + " no encontrado"));
    }

    @Operation(summary = "Eliminar un álbum",
            description = "Elimina un álbum",
            responses = {
                @ApiResponse(responseCode = "200", description = "Álbum eliminado exitosamente"),
                @ApiResponse(responseCode = "400", description = "Datos inválidos")
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
                @ApiResponse(responseCode = "200", description = "Álbum encontrado"),
                @ApiResponse(responseCode = "400", description = "Datos inválidos")
            })
    public Page<Album> buscarAlbumesPorFiltro(String filtro, Pageable pageable) {
        return albumRepo.buscarPorFiltro(filtro, pageable);
    }

    @Operation(summary = "Listar un álbum",
            description = "Listar todos los albumes",
            responses = {
                @ApiResponse(responseCode = "200", description = "Todos los albumes listados"),
                @ApiResponse(responseCode = "400", description = "Datos inválidos")
            })
    public List<Album> listarAlbumes() {
        return albumRepo.findAll();
    }

}
