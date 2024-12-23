package com.example.streaming_service.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.streaming_service.entidades.Cancion;

public interface CancionRepository extends JpaRepository<Cancion, Integer> {

    //Se usa CONCAT para concatenar los comodines (%) al parámetro :filtro porque en JPQL
    // no se permite usar los comodines directamente dentro de la consulta (e.g., LIKE %:filtro%).
    // Esta es una limitación de JPQL, a diferencia de SQL nativo.
    @Query("SELECT c FROM Cancion c WHERE LOWER(c.titulo) LIKE LOWER(CONCAT('%', :filtro, '%')) OR CAST(c.duracion AS string) LIKE CONCAT('%', :filtro, '%')")
    Page<Cancion> buscarPorFiltro(@Param("filtro") String filtro, Pageable pageable);

}
