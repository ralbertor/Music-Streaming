package com.example.streaming_service.entidades;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
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

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "FECHANACIMIENTO", nullable = false)
    private Date fechaNacimiento;

    @Column(name = "NACIONALIDAD", nullable = false)
    private String nacionalidad;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL)
    private List<Album> albumes;

    @ManyToMany
    @JoinTable(
        name = "artista_cancion",
        joinColumns = @JoinColumn(name = "artista_id"),
        inverseJoinColumns = @JoinColumn(name = "cancion_id")
    )
    private List<Cancion> canciones;

}
