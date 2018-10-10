package br.com.fiap.healthtrack.database;

import java.sql.Connection;

public interface ConnectionManagerJDBC {

	public Connection getConnection();
	public void closeConnection();
	
}
