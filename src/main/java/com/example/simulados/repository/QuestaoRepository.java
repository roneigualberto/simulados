package com.example.simulados.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.simulados.model.Disciplina;
import com.example.simulados.model.Questao;

public interface QuestaoRepository extends JpaRepository<Questao, Long> {

	List<Questao> findByDisciplina(Disciplina disciplina);

}
