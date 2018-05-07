package com.example.simulados.builder;

import com.example.simulados.model.Disciplina;
import com.example.simulados.model.Questao;

public class QuestaoBuilder {

	public static final String ENUNCIADO_DEFAULT = "Enunciado Questao A";

	private Questao questao;

	private QuestaoBuilder(Disciplina disciplina) {

		questao = new Questao();

		questao.setDisciplina(disciplina);
		questao.setEnunciado(ENUNCIADO_DEFAULT);
		questao.addAlternativa("Alternativa A");
		questao.addAlternativa("Alternativa B");
		questao.addAlternativa("Alternativa C");
		questao.addAlternativa("Alternativa D");
		questao.addAlternativa("Alternativa E", true);
	}

	private QuestaoBuilder(Questao questao) {

		 this.questao = questao;

	}

	public static QuestaoBuilder createQuestao(Disciplina disciplina) {
		return new QuestaoBuilder(disciplina);
	}

	public static QuestaoBuilder questao(Questao questao) {
		return new QuestaoBuilder(questao);
	}

	public Questao build() {
		return this.questao;
	}

}
