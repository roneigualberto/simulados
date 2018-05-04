package com.example.simulados.repository;

import static com.example.simulados.builder.DisciplinaBuilder.DEFAULT_DESCRICAO;
import static com.example.simulados.builder.DisciplinaBuilder.DEFAULT_NOME;
import static com.example.simulados.builder.DisciplinaBuilder.createDisciplina;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import com.example.simulados.model.Disciplina;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DisciplinaRepositoryTest {

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	private Disciplina disciplina;

	@Before
	public void setUp() {

		disciplina = createDisciplina().build();
		disciplina = disciplinaRepository.save(disciplina);
	}

	@Test
	public void deveSalvar() {
		assertThat(disciplina.getId(), notNullValue());
		assertThat(disciplina.getNome(), is(DEFAULT_NOME));
		assertThat(disciplina.getDescricao(), is(DEFAULT_DESCRICAO));
	}

	@Test
	public void deveSalvarComDescricaoNula() {

		Disciplina novaDisciplina = createDisciplina().comNome("Disciplina Com Descrição Nula").comDescricao(null)
				.build();

		Disciplina disciplinaPosSave = disciplinaRepository.save(novaDisciplina);

		assertThat(disciplinaPosSave.getId(), notNullValue());
		assertThat(disciplinaPosSave.getDescricao(), nullValue());

	}

	@Test
	public void naoDeveSalvarComNomeNulo() {

		try {
			Disciplina novaDisciplina = createDisciplina().comNome(null).build();
			disciplinaRepository.save(novaDisciplina);
			fail();
		} catch (TransactionSystemException ex) {

			ConstraintViolation<?> violation = getViolations(ex).stream().findFirst().get();
			assertThat(violation.getMessage(), is("não pode ser nulo"));
		}

	}

	private Set<ConstraintViolation<?>> getViolations(TransactionSystemException ex) {
		ConstraintViolationException cvex = (ConstraintViolationException) ex.getRootCause();
		Set<ConstraintViolation<?>> violations = cvex.getConstraintViolations();
		return violations;
	}

	@Test
	public void deveAtualizar() {

		disciplina = createDisciplina(disciplina).comNome("Disciplina A1").comDescricao("Disciplina A1 Desc").build();

		Disciplina displinaPosUpdate = disciplinaRepository.saveAndFlush(disciplina);

		assertThat(displinaPosUpdate.getNome(), is(disciplina.getNome()));
		assertThat(displinaPosUpdate.getDescricao(), is(disciplina.getDescricao()));

	}

	@Test
	public void deveDeletar() {

		Disciplina disciplinaDelete = createDisciplina().comNome("Disciplina Delete")
				.comDescricao("Disciplina Delete Desc").build();

		disciplinaDelete = disciplinaRepository.save(disciplinaDelete);

		disciplinaRepository.delete(disciplinaDelete);

		Optional<Disciplina> discOpt = disciplinaRepository.findById(disciplinaDelete.getId());

		assertThat(discOpt.isPresent(), is(false));
	}

	@Test
	public void deveBuscarPorNome() {

		Optional<Disciplina> disciplinaOpt = disciplinaRepository.findByNome(DEFAULT_NOME);

		assertThat(disciplinaOpt.isPresent(), is(true));
	}

	@After
	public void tearDown() {
		disciplinaRepository.delete(disciplina);
	}

}
