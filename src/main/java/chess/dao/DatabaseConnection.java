package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/chess";
	private static final String USER = "user";
	private static final String PASSWORD = "password";
	private static final String CONNECTION_ERROR = "현재 데이터 베이스를 연결할 수 없습니다.";

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			throw new IllegalStateException(CONNECTION_ERROR);
		}
	}
}
