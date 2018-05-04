package com.example.simulados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.simulados.model.Disciplina;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long>  {

	
	Optional<Disciplina> findByNome(String nome);
}
