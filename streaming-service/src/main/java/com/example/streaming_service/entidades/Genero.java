package com.example.streaming_service.entidades;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
@Schema(description="Información de los géneros")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Schema(description="Identificador del género")
    private int id;
    @Column(name = "NOMBRE")
    @Schema(description="Nombre del género")
    private String nombre;
    @Column(name = "DESCRIPCION")
    @Schema(description="Descripción del género")
    private String descripcion;
    @Column(name = "ANOORIGEN")
    @Schema(description="Año de origen del género")
    private int anoOrigen;

    @ManyToMany(mappedBy = "generos")
    @Schema(description="Canciones a los que pertenecen el género")
    private List<Cancion> canciones;

}
