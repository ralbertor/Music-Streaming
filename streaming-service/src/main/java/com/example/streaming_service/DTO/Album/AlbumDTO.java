package com.example.streaming_service.DTO.Album;

import java.util.List;

import com.example.streaming_service.DTO.Cancion.CancionDTO;

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

    private List<CancionDTO> canciones;

    public AlbumDTO(){}
    

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoLanzamiento() {
        return this.anoLanzamiento;
    }

    public void setAnoLanzamiento(int anoLanzamiento) {
        this.anoLanzamiento = anoLanzamiento;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumeroCanciones() {
        return this.numeroCanciones;
    }

    public void setNumeroCanciones(int numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }

    public String getUrlPortada() {
        return this.urlPortada;
    }

    public void setUrlPortada(String urlPortada) {
        this.urlPortada = urlPortada;
    }

    public List<CancionDTO> getCanciones() {
        return this.canciones;
    }

    public void setCanciones(List<CancionDTO> canciones) {
        this.canciones = canciones;
    }
    
}
