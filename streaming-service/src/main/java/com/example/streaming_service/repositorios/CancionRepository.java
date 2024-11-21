package com.example.streaming_service.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.streaming_service.entidades.Cancion;

public interface CancionRepository extends JpaRepository<Cancion, Integer> {

    @Query("SELECT a FROM Cancion a WHERE LOWER(a.titulo) LIKE LOWER(CONCAT('%', :filtro, '%')) OR CAST(a.duracion AS string) LIKE CONCAT('%', :filtro, '%')")
Page<Cancion> buscarPorFiltro(@Param("filtro") String filtro, Pageable pageable);


}
