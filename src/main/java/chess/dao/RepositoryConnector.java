package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositoryConnector {

	private final Connection connection;

	public RepositoryConnector() {
		this.connection = getConnection();
	}

	private static Connection getConnection() {
		Connection con = null;
		String server = "127.0.0.1:13306"; // MySQL 서버 주소
		String database = "db_name"; // MySQL DATABASE 이름
		String option = "?useSSL=false&serverTimezone=UTC";
		String userName = "root"; //  MySQL 서버 아이디
		String password = "root"; // MySQL 서버 비밀번호
		// 드라이버 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
			e.printStackTrace();
		}
		// 드라이버 연결
		try {
			con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}

	public ResultSet executeQuery(String query, String... inputs) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(query);
		for (int i = 1; i <= inputs.length; i++) {
			statement.setString(i, inputs[i - 1]);
		}
		return statement.executeQuery();
	}

	public int executeUpdate(String query, String... inputs) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(query, new String[] {"id"});
		for (int i = 1; i <= inputs.length; i++) {
			statement.setString(i, inputs[i - 1]);
		}
		statement.executeUpdate();
		ResultSet result = statement.getGeneratedKeys();
		result.next();
		return result.getInt(1);
	}
}
