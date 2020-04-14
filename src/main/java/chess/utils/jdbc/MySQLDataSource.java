package chess.utils.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDataSource implements DataSource {
	private static final String SERVER = "localhost:13306";
	private static final String DATABASE = "chess";
	private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
	private static final String URL = String.format("jdbc:mysql://%s/%s%s", SERVER, DATABASE, OPTION);
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";

	@Override
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
	}
}
