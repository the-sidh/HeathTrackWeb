package br.com.fiap.healthtrack.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.fiap.healthtrack.medidas.OperacoesMedidasHelper;

public class ConnectionManagerOracleImpl implements ConnectionManagerJDBC {

	private static ConnectionManagerJDBC instance;
	
	public static ConnectionManagerJDBC getInstance() {
		if (instance == null) {
			instance = new ConnectionManagerOracleImpl();
		}
		return instance;
	}
	
	private Connection conection = null;

	@Override
	public Connection getConnection() {

		try {
			// Registra o Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Abre uma conexão
			conection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ht", "ht");
			conection.setAutoCommit(false);

			// Tratamento de erro
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conection;
	}

	@Override
	public void closeConnection() {
		try {
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}