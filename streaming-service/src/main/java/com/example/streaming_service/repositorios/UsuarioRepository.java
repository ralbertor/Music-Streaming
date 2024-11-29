package com.example.streaming_service.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.streaming_service.entidades.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
}

