package com.example.streaming_service.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.streaming_service.entidades.Album;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
