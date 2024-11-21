package com.example.streaming_service.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.streaming_service.entidades.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

    @Query("SELECT a FROM Artista a WHERE "
            + "LOWER(a.nombre) LIKE LOWER(CONCAT('%', :filtro, '%')) OR "
            + "LOWER(a.nacionalidad) LIKE LOWER(CONCAT('%', :filtro, '%')) OR "
            + "CAST(a.fechaNacimiento AS string) LIKE CONCAT('%', :filtro, '%')")
    Page<Artista> buscarPorFiltro(@Param("filtro") String filtro, Pageable pageable);

}
