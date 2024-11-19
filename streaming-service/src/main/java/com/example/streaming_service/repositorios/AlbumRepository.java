package com.example.streaming_service.repositorios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.streaming_service.entidades.Album;


public interface AlbumRepository extends JpaRepository<Album, Integer> {

     // Método para buscar por atributos
    @Query("SELECT a FROM Album a WHERE"
            + "LOWER(a.titulo) LIKE LOWER(CONCAT('%', :filtro, '%')) OR "
            + "LOWER(a.anoLanzamiento) LIKE LOWER(CONCAT('%', :filtro, '%')) OR "
            + "LOWER(a.descripcion) LIKE LOWER(CONCAT('%', :filtro, '%')) OR "
            + "LOWER(a.numeroCanciones) LIKE LOWER(CONCAT('%', :filtro, '%')) OR "
            + "LOWER(a.urlPortada) LIKE LOWER(CONCAT('%', :filtro, '%')) OR ")
    public Page<Album> buscarPorFiltro(String filtro, Pageable pageable);

}
