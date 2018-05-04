package com.example.simulados.builder;

import com.example.simulados.model.Disciplina;
import com.example.simulados.model.Usuario;

public class UsuarioBuilder {

	public static final String DEFAULT_USER_NAME = "usuario";
	public static final String DEFAULT_NOME = "Usuario A";
	public static final String DEFAULT_EMAIL = DEFAULT_USER_NAME + "@email.com";
	public static final String DEFAULT_SENHA = DEFAULT_USER_NAME + "123";

	private Usuario usuario;

	private UsuarioBuilder() {

		usuario = new Usuario();
		usuario.setUsername(DEFAULT_USER_NAME);
		usuario.setNome(DEFAULT_NOME);
		usuario.setEmail(DEFAULT_EMAIL);
		usuario.setSenha(DEFAULT_SENHA);
	}

	private UsuarioBuilder(Usuario usuario) {
		this.usuario = usuario;
	}

	public static UsuarioBuilder createUsuario() {
		return new UsuarioBuilder();
	}

	public static UsuarioBuilder createUsuario(Usuario usuario) {
		return new UsuarioBuilder(usuario);
	}

	public Usuario build() {

		return usuario;

	}

	public UsuarioBuilder comNome(String nome) {

		usuario.setNome(nome);
		return this;
	}

	public UsuarioBuilder comUsername(String username) {

		usuario.setUsername(username);

		return this;
	}

	public UsuarioBuilder comEmail(String email) {
		usuario.setEmail(email);
		return this;
	}

	public UsuarioBuilder comSenha(String senha) {
		usuario.setSenha(senha);
		return this;
	}

}
