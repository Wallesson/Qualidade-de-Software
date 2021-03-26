package edu.uepb.web.biblioteca.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.enums.TipoNivel;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Curso;
import edu.uepb.web.biblioteca.service.AlunoService;
import edu.uepb.web.biblioteca.service.FuncionarioService;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class AlunoDAOTest {
	Aluno aluno1;
	Aluno aluno2;
	Aluno aluno3;
	Aluno aluno4;

	Curso quimica;
	Curso psicologia;
	Curso farmacia;
	Curso matematica;
	Curso relacoes;

	CursoDAOImpl cursoDAO;
	AlunoDAOImpl manager;
	AlunoService alunoService;
	Connection conn;

	@Before
	public void setUp() throws Exception {
		conn = new Conexao().getConexao();
		alunoService = new AlunoService();
		manager = new AlunoDAOImpl();
		cursoDAO = new CursoDAOImpl();

		quimica = new Curso("Quimica", TipoNivel.GRADUACAO, "Exata");
		psicologia = new Curso("Psicologia", TipoNivel.GRADUACAO, "Humanas");
		farmacia = new Curso("Farmacia", TipoNivel.GRADUACAO, "Saude");
		matematica = new Curso("Matematica", TipoNivel.GRADUACAO, "Exatas");
		relacoes = new Curso("Relacoes Int", TipoNivel.GRADUACAO, "Sociais");

		aluno1 = new Aluno("", "435", "34331", "Lula", "Dina", "Brasil", "Sao Paulo", "9893434", null, "2016", "2",
				"dsenr", null);

		aluno2 = new Aluno("", "wedfd", "0634.401-21", "Kaio", "Dilma", "Brasil", "Jooa Pessoa", "9893434", null,
				"2016", "2", "eriaa", null);

		aluno3 = new Aluno("", "4s3", "164.002-92", "Herculer", "Jean", "Brasil", "Recife", "9893434", null, "2011",
				"1", "lsjoin", null);

		aluno4 = new Aluno("", "dsd23", "236002-02", "Hermano", "Jeni", "Timor", "Forta", "98475", null, "2017", "2",
				"BHYD", null);
	}

	@Test
	public void inserir() {
		CursoDAOImpl aux = new CursoDAOImpl();
		aluno1 = aux.aux(aluno1, farmacia);
		aluno1.setMatricula(alunoService.gerarMatricula(aluno1));
		int idaluno = manager.inserir(aluno1);

		if (idaluno < 0) {
			Assert.fail();
		}

	}

	@Test
	public void get() {
		int idCurso = cursoDAO.inserir(psicologia);
		aluno2.setCurso(cursoDAO.getById(idCurso));

		aluno2.setMatricula(alunoService.gerarMatricula(aluno2));
		int id = manager.inserir(aluno2);
		assertNotEquals(null, manager.getById(id));
	}

	@Test
	public void getLista() {
		List<Aluno> listaAluno = manager.getLista();
		if (listaAluno.size() < 0) {
			Assert.fail();
		}
	}

	@Test
	public void remover() {
		int idCurso = cursoDAO.inserir(matematica);
		aluno3.setCurso(cursoDAO.getById(idCurso));

		int id = manager.inserir(aluno3);
		aluno3.setMatricula(alunoService.gerarMatricula(aluno3));
		manager.remover(aluno3);
		assertEquals(null, manager.getById(id));
	}

	@Test
	public void atualizar() {
		int idCurso = cursoDAO.inserir(quimica);
		aluno4.setCurso(cursoDAO.getById(idCurso));

		aluno4.setMatricula(alunoService.gerarMatricula(aluno4));
		int id = manager.inserir(aluno4);
		aluno4.setId(id);
		aluno4.setEndereco("Canada");

		manager.atualizar(aluno4);

		assertEquals("Canada", manager.getById(id).getEndereco());

	}

	@Test
	public void isExiste() {

		Aluno aluno5 = new Aluno();
		CursoDAOImpl aux = new CursoDAOImpl();
		
		aluno5 = aux.aux2(aluno5, relacoes);
		aluno5.construtor("3454", "re3454", "Fred", "Jina", "Holanda", "Rua Hjas", "23454", "2017", "2","erto", null);


		assertEquals(false, manager.isExiste(aluno5));
	}

}
