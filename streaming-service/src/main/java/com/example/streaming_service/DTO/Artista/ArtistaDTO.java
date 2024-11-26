package com.example.streaming_service.DTO.Artista;

import java.util.Date;

import lombok.Data;

@Data
public class ArtistaDTO {

    private String nombre;
    private Date fechaNacimiento;
    private String nacionalidad;
    //private List<ArtistaAlbumDTO> albumes;
    //private List<ArtistaCancionDTO> canciones;

    
}
