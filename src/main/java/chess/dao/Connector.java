package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jdbc.DataAccessException;

public class Connector {
	private static final Connection CONNECTION;

	static {
		String server = "localhost:3306"; // MySQL 서버 주소
		String database = "chess"; // MySQL DATABASE 이름
		String option = "?useSSL=false&serverTimezone=UTC";
		String userName = "woowa2"; //  MySQL 서버 아이디
		String password = "test123"; // MySQL 서버 비밀번호

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			CONNECTION = DriverManager.getConnection(
				"jdbc:mysql://" + server + "/" + database + option, userName, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new DataAccessException(e);
		}
	}

	public static Connection getConnection() {
		return CONNECTION;
	}

}
