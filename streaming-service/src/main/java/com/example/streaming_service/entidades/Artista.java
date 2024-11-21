package com.example.streaming_service.entidades;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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

    @ManyToMany
    @JoinTable(name = "artistaCancion", joinColumns =
    @JoinColumn(name = "artistaId"), inverseJoinColumns =
    @JoinColumn(name = "cancionId") )
    private List<Cancion> canciones;

}
