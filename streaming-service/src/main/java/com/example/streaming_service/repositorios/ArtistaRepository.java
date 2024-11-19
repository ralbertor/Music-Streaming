package com.example.streaming_service.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.streaming_service.entidades.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

    // Método para buscar por atributos
    @Query("SELECT a FROM Artista a WHERE"
            + "LOWER(a.nombre) LIKE LOWER(CONCAT('%', :filtro, '%')) OR "
            + "LOWER(a.nacionalidad) LIKE LOWER(CONCAT('%', :filtro, '%')) OR "
            )
    public Page<Artista> buscarPorFiltro(String filtro, Pageable pageable);


}
