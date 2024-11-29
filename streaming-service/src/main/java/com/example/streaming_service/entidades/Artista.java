package com.example.streaming_service.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Información de un artista")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Schema(description="Identificador del artista")
    private int id;

    @Schema(description = "Nombre del artista")
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Schema(description = "Fecha de nacimiento del artista")
    @Column(name = "FECHANACIMIENTO", nullable = false)
    private Date fechaNacimiento;

    @Schema(description="Nacionalidad del artista")
    @Column(name = "NACIONALIDAD", nullable = false)
    private String nacionalidad;

    @Schema(description="Albumes del artista")
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL)
    private List<Album> albumes;

    @Schema(description = "Canciones del artista")
    @ManyToMany
    @JoinTable(
        name = "artista_cancion",
        joinColumns = @JoinColumn(name = "artista_id"),
        inverseJoinColumns = @JoinColumn(name = "cancion_id")
    )
    private List<Cancion> canciones = new ArrayList<>();

}
