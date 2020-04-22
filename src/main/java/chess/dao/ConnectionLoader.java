package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionLoader {
	private static final String SERVER = "127.0.0.1:3306";
	private static final String DB_NAME = "db_name";
	private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
	private static final String NAME = "root";
	private static final String PASSWORD = "ans230";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

	public static Connection load() throws ClassNotFoundException, SQLException {
		Connection con = null;
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
			throw new ClassNotFoundException("데이터베이스에 연결할 수 없습니다.");
		}
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DB_NAME + OPTION, NAME, PASSWORD);
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
			throw new SQLException("데이터베이스에 연결할 수 없습니다.");
		}
		return con;
	}
}
