package com.db;

import java.sql.*;

public class dbquery {

	private static final String URL = "jdbc:sqlserver://locaholst:1433;databaseName=test;";
	private static final String NAME = "";
	private static final String PASSWORD = "";
	private static Connection conn = null;

	static {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(URL, NAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return conn;
	}
}