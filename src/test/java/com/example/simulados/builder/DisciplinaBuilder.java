package com.example.simulados.builder;

import com.example.simulados.model.Disciplina;

public class DisciplinaBuilder {

	public static final String DEFAULT_NOME = "Disciplina A";
	public static final String DEFAULT_DESCRICAO = DEFAULT_NOME + " Desc";

	private Disciplina disciplina;

	private DisciplinaBuilder() {

		disciplina = new Disciplina();
		disciplina.setNome(DEFAULT_NOME);
		disciplina.setDescricao(DEFAULT_DESCRICAO);
	}

	private DisciplinaBuilder(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public static DisciplinaBuilder createDisciplina() {
		return new DisciplinaBuilder();
	}

	public static DisciplinaBuilder createDisciplina(Disciplina disciplina) {
		return new DisciplinaBuilder(disciplina);
	}

	public Disciplina build() {

		return disciplina;

	}

	public DisciplinaBuilder comNome(String nome) {

		disciplina.setNome(nome);
		return this;
	}

	public DisciplinaBuilder comDescricao(String desc) {

		disciplina.setDescricao(desc);

		return this;
	}

}
