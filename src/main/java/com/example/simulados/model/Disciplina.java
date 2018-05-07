package com.example.simulados.model;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "disciplina")
@SequenceGenerator(name = "disciplina_sg", sequenceName = "disciplina_seq", allocationSize = 1)
public class Disciplina {

	@Id
	@GeneratedValue(generator = "disciplina_sg", strategy = SEQUENCE)
	private Long id;

	@NotNull
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@Column(name = "descricao", length = 255)
	private String descricao;
	
	

	public Disciplina(Long id, String nome, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
