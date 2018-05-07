package com.example.simulados.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "alternativa")
@SequenceGenerator(name = "alternativa_sg", sequenceName = "alternativa_seq", allocationSize = 1)
public class Alternativa {

	@Id
	@GeneratedValue(generator = "alternativa_sg", strategy = SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "questao_id", nullable = false)
	private Questao questao;

	

	@Column(name = "descricao", nullable = false, length = 255)
	private String descricao;
	
	@Column(name = "correta", nullable = false)
	private boolean correta;

	public Alternativa() {
		// TODO Auto-generated constructor stub
	}

	public Alternativa(Questao questao, String descricao, boolean correta) {
		super();
		this.questao = questao;
		this.descricao = descricao;
		this.correta = correta;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	
	
	



	

	public boolean isCorreta() {
		return correta;
	}

	public void setCorreta(boolean correta) {
		this.correta = correta;
	}
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alternativa other = (Alternativa) obj;
		
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		
		
		return true;
	}

}
