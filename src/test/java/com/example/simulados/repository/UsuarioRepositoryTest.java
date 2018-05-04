package com.example.simulados.repository;

import static com.example.simulados.builder.UsuarioBuilder.DEFAULT_EMAIL;
import static com.example.simulados.builder.UsuarioBuilder.DEFAULT_NOME;
import static com.example.simulados.builder.UsuarioBuilder.DEFAULT_USER_NAME;
import static com.example.simulados.builder.UsuarioBuilder.createUsuario;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.simulados.builder.UsuarioBuilder;
import com.example.simulados.model.Perfil;
import com.example.simulados.model.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository usuarioRepository;

	private Usuario usuario;

	@Before
	public void setUp() {

		usuario = UsuarioBuilder.createUsuario().build();

		usuarioRepository.save(usuario);
	}

	@Test
	public void deveSalvar() {
		assertThat(usuario.getId(), notNullValue());
		assertThat(usuario.getNome(), is(DEFAULT_NOME));
		assertThat(usuario.getUsername(), is(DEFAULT_USER_NAME));
		assertThat(usuario.getEmail(), is(DEFAULT_EMAIL));
		assertThat(usuario.getSenha(), is(UsuarioBuilder.DEFAULT_SENHA));
		assertThat(usuario.getPerfil(), is(Perfil.USR_APP));
	}

	@Test
	public void deveAtualizar() {

		Usuario usuarioUpdate = createUsuario(usuario).comNome("Usuario 2 Update").comEmail("usuario2@email.com")
				.comSenha("1234").build();

		usuarioRepository.saveAndFlush(usuarioUpdate);

		Usuario usuarioPosUpdate = usuarioRepository.findById(usuarioUpdate.getId()).get();

		assertThat(usuarioPosUpdate.getNome(), is("Usuario 2 Update"));
		assertThat(usuarioPosUpdate.getEmail(), is("usuario2@email.com"));
		assertThat(usuarioPosUpdate.getSenha(), is("1234"));

	}

	@Test
	public void naoDeveAtualizarUsername() {

		Usuario usuarioUpdate = createUsuario(usuario).comUsername("usuario2").build();

		usuarioRepository.saveAndFlush(usuarioUpdate);

		Usuario usuarioPosUpdate = usuarioRepository.findById(usuarioUpdate.getId()).get();

		assertThat(usuarioPosUpdate.getUsername(), is(DEFAULT_USER_NAME));

	}

	@After
	public void tearDown() {
		usuarioRepository.delete(usuario);
	}

}
