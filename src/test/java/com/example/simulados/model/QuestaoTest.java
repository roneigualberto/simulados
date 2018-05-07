package com.example.simulados.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QuestaoTest {

	private Questao questao;

	@Before
	public void setUp() {

		Disciplina disciplina = new Disciplina(1l, "Disciplina A", "Disciplina A Desc");
		questao = new Questao(disciplina, "Anunciado A");
	}

	@Test
	public void deveAdicionarAlternativas() {

		Alternativa altA = new Alternativa(null, "Alternativa A", false);
		Alternativa altB = new Alternativa(null, "Alternativa B", true);

		questao.addAlternativa(altA);
		questao.addAlternativa(altB);

		assertThat(questao.qtdeAlternativas(), is(2));
		assertThat(altA.getQuestao(), is(questao));
		assertThat(altB.getQuestao(), is(questao));

	}
	
	@Test
	public void deveRemoverAlternativas() {

		Alternativa altA = new Alternativa(null, "Alternativa A", false);
		Alternativa altB = new Alternativa(null, "Alternativa B", true);

		questao.addAlternativa(altA);
		questao.addAlternativa(altB);
		
		questao.removeAlternativa(altA);

		assertThat(questao.qtdeAlternativas(), is(1));
		assertThat(altA.getQuestao(), nullValue());
		assertThat(altB.getQuestao(), is(questao));

	}
	
	@Test
	public void deveRemoverAlternativaCorreta() {

		Alternativa altA = new Alternativa(null, "Alternativa A", false);
		Alternativa altB = new Alternativa(null, "Alternativa B", true);

		questao.addAlternativa(altA);
		questao.addAlternativa(altB);
		
		questao.removeAlternativa(altB);

		assertNull(questao.getCorreta());

	}
	
	

	@Test
	public void deveAdicionarAlternativaDuplicada() {

		Alternativa altA = new Alternativa(null, "Alternativa A", false);
		Alternativa altB = new Alternativa(null, "Alternativa A", true);

		questao.addAlternativa(altA);
		questao.addAlternativa(altB);

		assertThat(questao.qtdeAlternativas(), is(1));
		assertThat(altA.getQuestao(), is(questao));
		assertThat(altB.getQuestao(), nullValue());

	}

	@Test
	public void deveAdicionar2AlternativasCorretas() {

		Alternativa altA = new Alternativa(null, "Alternativa A", true);
		Alternativa altB = new Alternativa(null, "Alternativa B", true);

		questao.addAlternativa(altA);
		questao.addAlternativa(altB);

		assertFalse(altA.isCorreta());
		assertThat(questao.getCorreta(), is(altB));

	}

	@Test
	public void deveAdicionarAlternativaNula() {

		try {
			questao.addAlternativa(null);
			Assert.fail();
		} catch (IllegalArgumentException e) {
			assertThat(e.getMessage(), is("Alternativa não pode ser nula"));
		}

	}

	@Test
	public void deveAdicionarAlternativaCorreta() {

		Alternativa altCorreta = new Alternativa(null, "Alternativa B", true);

		questao.addAlternativa(altCorreta);

		assertThat(questao.getCorreta(), is(altCorreta));
		assertThat(questao.isCorreta(altCorreta));

	}

}
