package com.example.streaming_service.entidades;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "FECHANACIMIENTO")
    private Date fechaNacimiento;
    @Column(name = "NACIONALIDAD")
    private String nacionalidad;

    @OneToMany(mappedBy = "artista")
    private List<Album> albumes;

}
