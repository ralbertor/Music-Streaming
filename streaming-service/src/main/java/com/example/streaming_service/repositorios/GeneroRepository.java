package com.example.streaming_service.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.streaming_service.entidades.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {

    @Query("SELECT g FROM Genero g WHERE g.nombre LIKE %:filtro%")
    Page<Genero> buscarPorFiltro(@Param("filtro") String filtro, Pageable pageable);

}
