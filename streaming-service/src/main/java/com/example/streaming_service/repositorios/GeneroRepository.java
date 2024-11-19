package com.example.streaming_service.repositorios;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.streaming_service.entidades.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Integer> {

    // Método para buscar por atributos
    @Query("SELECT a FROM Genero a WHERE"
            + "LOWER(a.nombre) LIKE LOWER(CONCAT('%', :filtro, '%')) OR "
            + "LOWER(a.descripcion) LIKE LOWER(CONCAT('%', :filtro, '%')) OR "
            + "LOWER(a.anoOrigen) LIKE LOWER(CONCAT('%', :filtro, '%')) OR ")
    public Page<Genero> buscarPorFiltro(String filtro, Pageable pageable);

}
