package edu.uepb.web.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.model.Curso;
import edu.uepb.web.biblioteca.model.Item;

/**
 * A classe para acessar os dados no banco associando ao objeto {@link Item}
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 */
public class ItemDAOImpl implements DAO<Item> {
	private static final int FAKE_ID = -1;
	private Item item;
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;

	private static Logger logger = Logger.getLogger(ItemDAOImpl.class);

	public ItemDAOImpl() {
		this.connection = new Conexao().getConexao();
	}

	/**
	 * @ @see {@link DAO#getById(int)}
	 */
	@Override
	public Item getById(int id) {
		logger.info("Executar o metodo 'get' do item" + id);

		String sql = "SELECT * FROM item WHERE item.id = ?";

		try {
			statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				item = new Item();
				item.setId(resultSet.getInt(1));
				item.setTipoItem(resultSet.getString(2));
				item.setIsbn(resultSet.getString(3));
				item.setTitulo(resultSet.getString(4));
				item.setTipoAnais(resultSet.getString(5));
				item.setTipoMidia(resultSet.getString(6));
				item.setTipoTrabalho(resultSet.getString(7));
				item.setAutor(resultSet.getString(8));
				item.setCongresso(resultSet.getString(9));
				item.setAnoPublicacao(resultSet.getString(10));
				item.setLocal(resultSet.getString(11));
				item.setEditora(resultSet.getString(12));
				item.setEdicao(resultSet.getString(13));
				item.setNumeroPagina(resultSet.getInt(14));
				item.setArea(resultSet.getString(15));
				item.setTema(resultSet.getString(16));
				item.setDataGravacao(resultSet.getString(17));
				item.setOrientador(resultSet.getString(18));
				item.setData(resultSet.getString(19));
				item.setQuantidade(resultSet.getInt(20));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("O item foi selecionado: " + item);
		return item;
	}

	/**
	 * @ @see {@link DAO#getLista()}
	 */
	@Override
	public List<Item> getLista() {
		logger.info("Executar o metodo 'getLista' do item");

		List<Item> listaAcervo = new ArrayList<>();
		String sql = "SELECT * FROM item";
		try {
			statement = (PreparedStatement) connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				item = new Item();
				
				listaAcervo.add(item);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("Pegar os itens: " + listaAcervo.toString());
		return listaAcervo;
	}

	/**
	 * @ @see {@link DAO#inserir(Object)}
	 */
	public void inserirAux(Item  obj) {
		logger.info("Executar o metodo 'inserir' do item" + obj);
	}
	public String inserirAuxSql(Item  obj) {
		String a = "INSERT INTO aluno (curso_id, matricula , rg, cpf, nome, mae,  naturalidade, endereco, telefone, ano, periodo, senha, email) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
		return a;
	}
	public boolean inserirAuxIsnull(Item  obj) {
		if(obj != null) {
			return true;
		}
		return false;
	}
	public void inserirAuxSet(int id,Item  obj) throws SQLException {
		statement = (PreparedStatement) connection.prepareStatement(inserirAuxSql(obj), Statement.RETURN_GENERATED_KEYS);
		statement.execute();
		resultSet = statement.getGeneratedKeys();

		if (resultSet.next()) {
			id = resultSet.getInt(1);
			obj.setId(id);
		}
		statement.close();
	}
	@Override
	public int inserir(Item obj) {
		inserirAux(obj);
		
		int id = ItemDAOImpl.FAKE_ID;
		if (inserirAuxIsnull(obj)) {

			try {
				inserirAuxSet(id,obj);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logger.info("O item foi inserido: " + obj);
		return id;
	}

	/**
	 * @ @see {@link DAO#remover(Object)}
	 */
	@Override
	public void remover(Item obj) {
		logger.info("Executar o metodo 'remover' do item" + obj);
		if (obj != null) {

			String sql = "DELETE FROM item WHERE item.id = ?";

			try {
				statement = (PreparedStatement) connection.prepareStatement(sql);
				statement.setInt(1, obj.getId());
				statement.execute();

				statement.close();
				logger.info("O item foi removido" + obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @ @see {@link DAO#atualizar(Object)}
	 */
	@Override
	public void atualizar(Item obj) {
		logger.info("Executar metodo 'atualizar' do Item: " + obj);
		if (obj != null) {

			String sql = "UPDATE item SET tipo_item = ?, isbn = ?, titulo = ?, tipo_anais = ?, tipo_midia = ?, tipo_trabalho_conclusao = ?, autor = ?, congresso = ?, ano_publicacao = ?, local = ?, editora = ?, edicao = ?, numero_pagina = ?, area = ?, tema = ?, data_gravacao = ?, orientador = ? , data = ?, quantidade = ? WHERE id = ?";

			try {
				statement = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				statement.execute();

				connection.close();
				logger.info("O item foi atualizado: " + obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @throws SQLException 
	 * @ @see {@link DAO#isExiste(Object)}
	 */
	
	public boolean isExiA(Item obj) throws SQLException {
		String sql = "SELECT * FROM item WHERE titulo = ?";
		statement = (PreparedStatement) connection.prepareStatement(sql);
		statement.setString(1, obj.getTitulo());
		resultSet = statement.executeQuery();

		if (resultSet.next()) {
			statement.close();
			logger.info("Esse item ja existe no banco: " + obj);
			return true;
		}
		statement.close();
		logger.info("Esse item nao existe no banco: " + obj);
		return false;
	}
	@Override
	public boolean isExiste(Item obj) {
		logger.info("Executar metodo 'isExiste' do Item: " + obj);
		if (obj != null) {
			try {
				 isExiA(obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logger.warn("O objeto item e invalido/null: " + obj);
		return false;
	}

}
