package com.example.streaming_service.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.streaming_service.entidades.Cancion;

public interface CancionRepository extends JpaRepository<Cancion, Integer> {
}
