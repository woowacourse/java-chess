package chess.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDataSource implements DataSource {
	private static final String server = "localhost:13306";
	private static final String database = "chess_db";
	private static final String option = "?useSSL=false&serverTimezone=UTC";
	private static final String userName = "root";
	private static final String password = "root";

	private MySQLDataSource() {
	}

	public static MySQLDataSource getInstance() {
		return LazyHolder.INSTANCE;
	}

	@Override
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(String.format("jdbc:mysql://%s/%s%s", server, database, option),
					userName, password);
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver load 오류: " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
		}
		return connection;
	}

	@Override
	public void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.err.println("connection 오류:" + e.getMessage());
		}
	}

	private static class LazyHolder {
		private static final MySQLDataSource INSTANCE = new MySQLDataSource();
	}
}
