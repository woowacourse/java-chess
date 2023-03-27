package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnector {

	private static final String SERVER = "localhost:13306";
	private static final String DATABASE = "chess";
	private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
	private static final String USERNAME = "user";
	private static final String PASSWORD = "password";

	public Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
		} catch (final SQLException e) {
			throw new RuntimeException("DB 연결 오류: " + e.getMessage());
		}
	}

	public boolean isDBNotConnected() {
		try {
			getConnection();
		} catch (final RuntimeException e) {
			return true;
		}
		return false;
	}
}
