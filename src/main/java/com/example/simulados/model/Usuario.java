package com.example.simulados.model;

import static com.example.simulados.model.Perfil.USR_APP;
import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USUARIO")
@SequenceGenerator(name = "USUARIO_SG", sequenceName = "USUARIO_SEQ", allocationSize = 1)
public class Usuario {

	@Id
	@GeneratedValue(generator = "USUARIO_SG", strategy = SEQUENCE)
	private Long id;

	@NotNull
	@Column(name = "username", length = 50, nullable = false, unique = true, updatable=false)
	private String username;

	@NotNull
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;

	@Email
	@NotNull
	@Column(name = "email", length = 255, nullable = false, unique = false)
	private String email;

	@NotNull
	@Column(name = "senha", length = 255, nullable = false)
	private String senha;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "perfil", nullable = false)
	private Perfil perfil = USR_APP;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
