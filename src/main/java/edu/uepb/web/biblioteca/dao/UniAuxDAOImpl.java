package edu.uepb.web.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.uepb.web.biblioteca.model.Universidade;

public class UniAuxDAOImpl{
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	public Universidade inserirAux(Universidade obj, int id,String sql) {
		try {
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, obj.getNome());
			statement.setString(2, obj.getEndereco());
			statement.setString(3, obj.getPeriodo());
			statement.setString(4, obj.getInicioPeriodo());
			statement.setString(5, obj.getFimPeriodo());
			
			statement.execute();
			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getInt(1);
				obj.setId(id);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
