package edu.uepb.web.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Curso;

/**
 * A classe para acessar os dados no banco associando ao objeto {@link Curso}
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 */
public class CursoDAOImpl implements DAO<Curso> {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private static Logger logger = Logger.getLogger(CursoDAOImpl.class);

	public CursoDAOImpl() {
		this.connection = new Conexao().getConexao();
	}
	
	public Aluno aux(Aluno aluno, Curso curso) {
		CursoDAOImpl cursoDAO = new CursoDAOImpl();
		aluno.setCurso(cursoDAO.getById(cursoDAO.inserir(curso)));
		return aluno;
	}
	public Aluno aux2(Aluno aluno, Curso relacoes) {
		CursoDAOImpl cursoDAO = new CursoDAOImpl();
		
		int idCurso = cursoDAO.inserir(relacoes);
		aluno.setCurso(cursoDAO.getById(idCurso));
		return aluno;
	}
	
	/**
	 * @ @see {@link DAO#getById(int)}
	 */
	@Override
	public Curso getById(int id) {
		logger.info("Executa o metodo 'get' do curso: " + id);

		

		String sql = "SELECT * FROM curso WHERE curso.id = ?";

		Curso curso = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				curso = new Curso();
				curso.setId(resultSet.getInt(1));
				curso.setNome(resultSet.getString(2));
				curso.setNivel(resultSet.getString(3));
				curso.setArea(resultSet.getString(4));
			}
			statement.close();
		} catch (SQLException e) {
			logger.error("Erro selecao o dado no base de dados", e);
			e.printStackTrace();
		}
		logger.info("O curso foi selecionado: " + curso);
		return curso;
	}

	/**
	 * @ @see {@link DAO#getLista()}
	 */
	@Override
	public List<Curso> getLista() {
		logger.info("Executa o metodo 'getLista' do curso");
		

		String sql = "SELECT * FROM curso";
		List<Curso> listaCurso = new ArrayList<Curso>();

		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Curso curso = new Curso();
			
				listaCurso.add(curso);
			}
			statement.close();
		} catch (SQLException e) {
			logger.error("Erro selecao o dado no base de dados", e);
			e.printStackTrace();
		}
		return listaCurso;
	}

	/**
	 * @ @see {@link DAO#inserir(Object)}
	 */
	
	public void inserirAux(Curso obj) {
		logger.info("Executa o metodo 'inserir' do curso : " + obj);
	}
	public String inserirAuxSql(Curso obj) {
		String a = "INSERT INTO aluno (curso_id, matricula , rg, cpf, nome, mae,  naturalidade, endereco, telefone, ano, periodo, senha, email) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
		return a;
	}
	public boolean inserirAuxIsnull(Curso obj) {
		if(obj != null) {
			return true;
		}
		return false;
	}
	public void inserirAuxSet(int id,Curso obj) throws SQLException {
		statement = connection.prepareStatement(inserirAuxSql(obj), Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, obj.getNome());
		statement.setString(2, obj.getNivel().name());
		statement.setString(3, obj.getArea());
		statement.execute();
		resultSet = statement.getGeneratedKeys();
		if (resultSet.next()) {
			id = resultSet.getInt(1);
			obj.setId(id);
		}
		statement.close();
	}
	@Override
	public int inserir(Curso obj) {
		inserirAux(obj);
		int id = -1;
		
		if (obj != null) {
			try {
				inserirAuxSet(id, obj);
			} catch (SQLException e) {
				logger.error("Erro insercao o dado no banco", e);
				e.printStackTrace();
			}
		}
		logger.info("O curso foi inserido: " + obj);
		return id;
	}

	/**
	 * @ @see {@link DAO#remover(Object)}
	 */
	@Override
	public void remover(Curso obj) {
		logger.info("Executa o metodo 'remover' do curso : " + obj);

		if (obj != null) {
			
			String sql = "DELETE FROM curso WHERE id = ?";

			try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, obj.getId());
				statement.execute();

				statement.close();
				logger.info("O curso foi removido" + obj);
			} catch (SQLException e) {
				logger.error("Erro remocao o dado no base de dados", e);
				e.printStackTrace();
			}
		}

	}

	/**
	 * @ @see {@link DAO#atualizar(Object)}
	 */
	@Override
	public void atualizar(Curso obj) {
		logger.info("Executa o metodo 'atualizar' do curso : " + obj);
		if (obj != null) {
			
			String sql = "UPDATE curso SET nome = ?, tipoNivel = ? , area = ? WHERE curso.id = ?";

			try {
				statement = connection.prepareStatement(sql);
			

				statement.execute();

				statement.close();
				logger.info("O item foi atualizado" + obj);
			} catch (SQLException e) {
				logger.error("Erro atualizacao o dado no banco", e);
				e.printStackTrace();
			}
		}
	}


	/**
	 * @throws SQLException 
	 * @ @see {@link DAO#isExiste(Object)}
	 */
	
	public boolean isExiA(Curso obj) throws SQLException {
		String sql = "SELECT * FROM curso WHERE nome = ?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, obj.getNome());
		resultSet = statement.executeQuery();

		if (resultSet.next()) {
			statement.close();
			logger.info("Esse curso ja existe no banco: " + obj);
			return true;
		}
		statement.close();
		logger.info("Esse curso nao existe no banco: " + obj);
		return false;
	}
	@Override
	public boolean isExiste(Curso obj) {
		logger.info("Executar metodo 'isExiste' do curso: " + obj);
		if (obj != null) {
			try {
				isExiA(obj);
			} catch (SQLException e) {
				logger.error("Erro selecao o dado no base de dados", e);
				e.printStackTrace();
			}
		}
		return false;
	}

}
