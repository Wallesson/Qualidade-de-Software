package edu.uepb.web.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.model.Funcionario;

/**
 * A classe para acessar os dados no banco associando ao objeto
 * {@link Funcionario}
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class FuncionarioDAOImpl implements DAO<Funcionario> {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private static final int ID_FAKE = -1;
	private static Logger logger = Logger.getLogger(FuncionarioDAOImpl.class);

	public FuncionarioDAOImpl() {
		this.connection = new Conexao().getConexao();
	}

	/**
	 * @throws SQLException 
	 * @see {@link DAO#getById(int)}
	 */
	
	
	/**
	 * @see {@link DAO#getLista()}
	 */
	@Override
	public List<Funcionario> getLista() {
		logger.info("Executa o metodo 'getLista' do funcionario");
		

		String sql = "SELECT * FROM funcionario";

		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Funcionario funcionario = new Funcionario();
				listaFuncionario.add(funcionario);
			}

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("Pegar os funcionarios: " + listaFuncionario.toString());
		return listaFuncionario;
	}

	/**
	 * @see {@link DAO#inserir(Object)}
	 */
	public void inserirAux(Funcionario obj) {
		logger.info("Executa o metodo 'inserir' do funcionario : " + obj);
	}
	public String inserirAuxSql(Funcionario obj) {
		String sql = "INSERT INTO emprestimo (emprestimo.funcionario_id, emprestimo.aluno_id, emprestimo.item_id, emprestimo.data_cadastrado, emprestimo.data_devolucao, entregou) VALUES (?,?,?,?,?,?)"; 
		return sql;
	}
	public boolean inserirAuxIsnull(Funcionario obj) {
		if(obj != null) {
			return true;
		}
		return false;
	}
	public void inserirAuxSet(int id,Funcionario obj) throws SQLException {
		statement = connection.prepareStatement(inserirAuxSql(obj), Statement.RETURN_GENERATED_KEYS);
		statement.execute();
		resultSet = statement.getGeneratedKeys();
		if (resultSet.next()) {
			id = resultSet.getInt(1);
			obj.setId(id);
		}
		statement.close();
	}
	
	@Override
	public int inserir(Funcionario obj) {
		
		inserirAux(obj); 
		int id = FuncionarioDAOImpl.ID_FAKE;
		if (inserirAuxIsnull(obj)) {

			try {
				inserirAuxSet(id, obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logger.info("O funcionario foi inserido: " + obj);
		return id;
	}

	/**
	 * @see {@link DAO#remover(Object)}
	 */
	@Override
	public void remover(Funcionario obj) {
		logger.info("Executa o metodo 'remover' funcionario : " + obj);
		if (obj != null) {
			
			String sql = "DELETE FROM funcionario WHERE funcionario.id = ?";

			try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, obj.getId());
				statement.execute();

				statement.close();
				logger.info("O funcionario foi removido" + obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see {@link DAO#atualizar(Object)}
	 */
	@Override
	public void atualizar(Funcionario obj) {
		logger.info("Executa o metodo 'atualizar' do funcionario : " + obj);
		if (obj != null) {
			
			String sql = "UPDATE funcionario SET nome = ?, tipo_funcionario = ? , cpf = ?, rg = ?, naturalidade = ?, endereco = ?, telefone = ?, email = ?, usuario = ?, senha = ? WHERE funcionario.id = ?";

			try {
				statement = connection.prepareStatement(sql);
				statement.execute();
				statement.close();
				logger.info("O funcionario foi atualizado" + obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @throws SQLException 
	 * @see {@link DAO#isExiste(Object)}
	 */
	public boolean isExisA(Funcionario obj) throws SQLException {
		String sql = "SELECT * FROM funcionario WHERE cpf = ?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, obj.getCpf());
		resultSet = statement.executeQuery();

		if (resultSet.next()) {
			statement.close();
			logger.info("Esse funcionario ja existe no banco: " + obj);
			return true;
		}
		statement.close();
		logger.info("Esse funcionario nao existe no banco: " + obj);
		return false;
	}
	
	@Override
	public boolean isExiste(Funcionario obj) {
		logger.info("Executar metodo 'isExiste' do funcionario: " + obj);
		if (obj != null) {
			try {
				isExisA( obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Autenticar o funcionario de acordo com parametros dados
	 * 
	 * @param matricula
	 * @param senha
	 * @return Aluno
	 * @throws SQLException 
	 * @throws AutenticacaoException
	 * @
	 */
	public Funcionario loginA(String usuario, String senha) throws SQLException, AutenticacaoException {
		Funcionario funcionario = null;
		String sql = "SELECT id, usuario, senha, tipo_funcionario FROM funcionario WHERE usuario = ?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, usuario);
		resultSet = statement.executeQuery();
		if (resultSet.next()) {
			if (resultSet.getString(3).equals(senha)) {
				funcionario = new Funcionario();
				funcionario.setId(resultSet.getInt(1));
				funcionario.setUsuario(resultSet.getString(2));
				funcionario.setSenha(resultSet.getString(3));
				funcionario.setTipoFunc(resultSet.getString(4));
			} else {
				throw new AutenticacaoException("Usuario ou Senha Invalida");
			}
		} else {
			throw new AutenticacaoException("Usuario ou Senha Invalida");
		}
		statement.close();
		return funcionario;
	}
	
	
	public Funcionario login(String usuario, String senha) throws AutenticacaoException {
		logger.info("Executar metodo 'login' do funcionario: " + usuario + " : " + senha);
		Funcionario funcionario = null;
		try {
			funcionario = loginA(usuario, senha); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("O funcionario foi autenticado: " + funcionario);
		return funcionario;
	}

	@Override
	public Funcionario getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
