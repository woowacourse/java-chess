package chess.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String SERVER = "localhost";
	private static final String DATABASE = "chess";
	private static final String USERNAME = "chess";
	private static final String PASSWORD = "chess123";
	private static final String CONNECTION_URL_FORMAT =
			"jdbc:mysql://%s/%s?useSSL=false&allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";

	private static Connection connection;

	private DatabaseConnection() {
		throw new AssertionError();
	}

	public static Connection getConnection() throws SQLException {
		if(connection != null && !connection.isClosed()) {
			return connection;
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
			e.printStackTrace();
		}

		try {
			String url = String.format(CONNECTION_URL_FORMAT, SERVER, DATABASE);
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
			e.printStackTrace();
		}
		return connection;
	}
}
