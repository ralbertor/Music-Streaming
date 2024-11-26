package com.example.streaming_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.streaming_service.DTO.Album.AlbumDTO;
import com.example.streaming_service.DTO.Artista.ArtistaDTO;
import com.example.streaming_service.DTO.Cancion.CancionDTO;
import com.example.streaming_service.entidades.Album;
import com.example.streaming_service.entidades.Artista;
import com.example.streaming_service.entidades.Cancion;
import com.example.streaming_service.repositorios.AlbumRepository;
import com.example.streaming_service.repositorios.ArtistaRepository;
import com.example.streaming_service.repositorios.CancionRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaRepository artistaRepo;
    
    @Autowired
    private AlbumRepository albumRepo;

    @Autowired
    private CancionRepository cancionRepo;

    @Operation(summary = "Crear un nuevo artista", 
        description = "Crea un artista con un álbum y canciones",
        responses = {
            @ApiResponse(responseCode="201", description = "Artista creado exitosamente"),
            @ApiResponse(responseCode= "400", description= "Datos inválidos")
        })

    public Artista createArtistaWithAlbumCanciones(ArtistaDTO artistaDTO, 
    List<AlbumDTO> albumDTOs, List<CancionDTO> cancionDTOs){
        //Crear aritista
        Artista artista = new Artista();
        artista.setNombre(artistaDTO.getNombre());
        artista.setFechaNacimiento(artistaDTO.getFechaNacimiento());
        artista.setNacionalidad(artistaDTO.getNacionalidad());
        
        //Guardar Artista
        artista = artistaRepo.save(artista);
        //Crear Albumes y agregar al artista
        List<Album> albums = new ArrayList<>();
        for (AlbumDTO albumDTO : albumDTOs) {
            Album album = new Album();
            album.setTitulo(albumDTO.getTitulo());
            album.setAnoLanzamiento(albumDTO.getAnoLanzamiento());
            album.setDescripcion(albumDTO.getDescripcion());
            album.setArtista(artista); 
        
            //Guardar el álbum
            album = albumRepo.save(album);

            //Agregar canciones al album
            List<Cancion> canciones = new ArrayList<>();
            for (CancionDTO cancionDTO : cancionDTOs) {
                Cancion cancion = new Cancion();
                cancion.setTitulo(cancionDTO.getTitulo());
                cancion.setDuracion(cancionDTO.getDuracion());
                cancion.setAlbum(album); //Asignar la canción al álbum
                
                //Guardar la cancion
                cancion = cancionRepo.save(cancion);
                canciones.add(cancion);
            }
            album.setCanciones(canciones); //Agregar canciones al album
            albums.add(album);
        }
        artista.setAlbumes(albums);
        return artistaRepo.save(artista);

    }

    @Operation(summary = "Crear un nuevo artista", 
        description = "Crea un artista con la información proporcionada",
        responses = {
            @ApiResponse(responseCode="201", description = "Artista creado exitosamente"),
            @ApiResponse(responseCode= "400", description= "Datos inválidos")
        })
    @Transactional
    public Artista createArtista(Artista artista) {
        if (artista.getNombre() == null || artista.getNacionalidad() == null) {
            throw new IllegalArgumentException("Nombre y nacionalidad obligatorios");
        }
        for(Cancion cancion : artista.getCanciones()){
            if(cancion.getArtistas()==null){
                cancion.setArtistas(new ArrayList<>());
            }
            cancion.getArtistas().add(artista);
        }
        return artistaRepo.save(artista);
    }
    @Operation(summary = "Actualizar un artista", 
        description = "Actualiza un artista",
        responses = {
            @ApiResponse(responseCode="200", description = "Artista actualizado correctamente"),
            @ApiResponse(responseCode= "400", description= "Datos inválidos")
        })
    @Transactional
    public Artista updateArtista(int id, Artista artista) {
        if (artistaRepo.existsById(id)) {
            Artista nuevoArtista = new Artista();
            nuevoArtista.setId(artista.getId());
            nuevoArtista.setNombre(artista.getNombre());
            nuevoArtista.setFechaNacimiento(artista.getFechaNacimiento());
            nuevoArtista.setNacionalidad(artista.getNacionalidad());
            if (nuevoArtista.getAlbumes() != null) {
                nuevoArtista.getAlbumes().clear();
                nuevoArtista.getAlbumes().addAll(artista.getAlbumes());
                artista.getAlbumes().forEach(album -> album.setArtista(nuevoArtista));
            }
            createArtista(nuevoArtista);
            return nuevoArtista;
        } else {
            return null;
        }
    }
    @Operation(summary = "Elimina un artista", 
        description = "Elimina un artista con la información proporcionada",
        responses = {
            @ApiResponse(responseCode="200", description = "Artista eliminado exitosamente"),
            @ApiResponse(responseCode= "400", description= "Datos inválidos")
        })
    @Transactional
    public void deleteArtista(int id) {
        if (artistaRepo.existsById(id)) {
            artistaRepo.deleteById(id);
            System.out.println("200 artista eliminado correctamente");
        } else {
            System.out.println("404, el artista no existe");
        }
    }
    @Operation(summary = "Buscar un artista", 
        description = "Busca un artista con el filtro dado",
        responses = {
            @ApiResponse(responseCode="200", description = "Artista encontrado"),
            @ApiResponse(responseCode= "400", description= "Datos inválidos")
        })
    public Page<Artista> buscarArtistasPorFiltro(String filtro, Pageable pageable) {
        return artistaRepo.buscarPorFiltro(filtro, pageable);
    }

}
