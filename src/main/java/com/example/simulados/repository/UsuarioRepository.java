package com.example.simulados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.simulados.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {

	
	Optional<Usuario> findByEmail(String email);
	Optional<Usuario> findByUsername(String username);
}
