package com.example.streaming_service.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.streaming_service.entidades.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {

    @Query("SELECT a FROM Album a WHERE "
            + "LOWER(a.titulo) LIKE LOWER(CONCAT('%', :filtro, '%')) OR "
            + "LOWER(CAST(a.anoLanzamiento AS string)) LIKE LOWER(CONCAT('%', :filtro, '%')) OR "
            + "LOWER(a.descripcion) LIKE LOWER(CONCAT('%', :filtro, '%')) OR "
            + "CAST(a.numeroCanciones AS string) LIKE CONCAT('%', :filtro, '%') OR "
            + "LOWER(a.urlPortada) LIKE LOWER(CONCAT('%', :filtro, '%'))")
    Page<Album> buscarPorFiltro(@Param("filtro") String filtro, Pageable pageable);
}
