package com.example.streaming_service.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.streaming_service.entidades.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {
}
