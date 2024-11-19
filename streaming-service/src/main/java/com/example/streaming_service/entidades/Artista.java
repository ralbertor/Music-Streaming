package com.example.streaming_service.entidades;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Artista {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private Date fechaNacimiento;
    private String nacionalidad;

    @OneToMany(mappedBy = "artista")
    private List<Album> albumes;  
}
