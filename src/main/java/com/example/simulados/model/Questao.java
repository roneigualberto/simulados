package com.example.simulados.model;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "questao")
@SequenceGenerator(name = "questao_gs", sequenceName = "questao_seq", allocationSize = 1)
public class Questao {

	@Id
	@GeneratedValue(generator = "questao_gs", strategy = SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "disciplina_id", nullable = false)
	private Disciplina disciplina;

	@OneToOne
	@JoinColumn(name = "alternativa_correta")
	private Alternativa correta;

	@Column(name = "enunciado", nullable = false, length = 255)
	private String enunciado;

	@OneToMany(mappedBy = "questao", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Alternativa> alternativas = new ArrayList<>();

	public Questao(Disciplina disciplina, String enunciado) {
		this(null, disciplina, enunciado);
	}
	
	public Questao(Long id,Disciplina disciplina, String enunciado) {
		this.id = id;
		this.disciplina = disciplina;
		this.enunciado = enunciado;
	}

	public Long getId() {
		return id;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public List<Alternativa> getAlternativas() {
		return alternativas;
	}

	public Alternativa getCorreta() {
		return correta;
	}

	public int qtdeAlternativas() {
		return this.alternativas.size();
	}

	public void addAlternativa(Alternativa alternativa) {

		if (alternativa == null) {
			throw new IllegalArgumentException("Alternativa não pode ser nula");
		}

		if (hasAlternativa(alternativa)) {
			return;
		}

		alternativa.setQuestao(this);
		this.alternativas.add(alternativa);

		if (alternativa.isCorreta()) {
			updateCorreta(alternativa);
		}

	}

	private void updateCorreta(Alternativa alternativa) {

		if (this.correta != null) {
			this.correta.setCorreta(false);
		}

		this.correta = alternativa;
	}

	public void removeAlternativa(Alternativa alternativa) {

		if (hasAlternativa(alternativa)) {

			alternativa.setQuestao(null);
			this.alternativas.remove(alternativa);

			if (isCorreta(alternativa)) {
				this.correta = null;
			}
		}
	}

	public boolean isCorreta(Alternativa alternativa) {
		return this.correta.equals(alternativa);
	}

	public boolean hasAlternativa(Alternativa alternativa) {
		return this.alternativas.contains(alternativa);
	}

}
