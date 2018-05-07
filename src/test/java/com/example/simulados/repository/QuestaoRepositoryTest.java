package com.example.simulados.repository;

import static com.example.simulados.builder.DisciplinaBuilder.createDisciplina;
import static com.example.simulados.builder.QuestaoBuilder.createQuestao;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.simulados.builder.QuestaoBuilder;
import com.example.simulados.model.Alternativa;
import com.example.simulados.model.Disciplina;
import com.example.simulados.model.Questao;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class QuestaoRepositoryTest {

	@Autowired
	private QuestaoRepository repository;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	private Disciplina disciplina;

	@Before
	public void setUp() {

		disciplina = disciplinaRepository.save(createDisciplina().build());
	}

	@Test
	public void deveSalvarComTodosCamposPreenchidos() {

		Questao questao = createQuestao(disciplina).build();

		Questao questaoSave = repository.save(questao);

		assertThat(questaoSave.getId(), notNullValue());
		assertThat(questaoSave.getEnunciado(), is(questao.getEnunciado()));
		assertThat(questaoSave.getAlternativas().size(), is(5));
		assertThat(questaoSave.getCorreta().getDescricao(), is("Alternativa E"));

	}

	@Test
	public void deveAdicionarAlternativa() {

		Questao questao = repository.save(createQuestao(disciplina).build());

		Long questaoId = questao.getId();

		questao.addAlternativa("Alternativa E");

		repository.saveAndFlush(questao);

		Questao questaoPosUpdate = repository.findById(questaoId).get();

		assertThat(questaoPosUpdate.qtdeAlternativas(), is(6));

	}

	@Test
	public void deveRemoverAlternativa() {

		Questao questao = repository.save(createQuestao(disciplina).build());

		Long questaoId = questao.getId();

		Alternativa alternativa = questao.getAlternativas().stream().findFirst().get();

		questao.removeAlternativa(alternativa);

		repository.saveAndFlush(questao);

		Questao questaoPosUpdate = repository.findById(questaoId).get();

		assertThat(questaoPosUpdate.qtdeAlternativas(), is(4));

	}

	@Test
	public void deveAtualizarAlternativas() {

		Questao questao = repository.save(createQuestao(disciplina).build());

		Long questaoId = questao.getId();

		List<Alternativa> alternativas = questao.getAlternativas();

		List<Alternativa> alternativasUpdate = new ArrayList<>();
		
		alternativasUpdate.add(alternativas.get(0));
		alternativasUpdate.add(alternativas.get(1));
		alternativasUpdate.add(alternativas.get(2));
		
		alternativasUpdate.add(new Alternativa(null, "Alternativa", false));
		
		questao.setAlternativas(alternativasUpdate);
		
		repository.saveAndFlush(questao);

		Questao questaoPosUpdate = repository.findById(questaoId).get();

		assertThat(questaoPosUpdate.qtdeAlternativas(), is(4));

	}

	@After
	public void tearDown() {
		// this.repository.deleteAll();
		// this.disciplinaRepository.deleteAll();
	}
}
