package com.aptech.hw5.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connect {
	private static final String USERNAME = "root";
	private static final String PASSWORD = "2002dev";
	private static final String CONN_STRING = "jdbc:mysql://localhost:3306/shop";
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
	}
	public static Connection getConnection(DB_Type type) throws SQLException, ClassNotFoundException {
		switch (type) {
		case SQL: 
			getConnection();
			break;
		case SQLite:
			break;	
		default:
			return getConnection();
		}
		return getConnection();
	}
}
