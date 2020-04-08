package chess.web.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcChessConnection {

	public static Connection getConnection() {
		Connection con = null;
		String server = "127.0.0.1:13306"; // MySQL 서버 주소
		String database = "level_1_chess_db"; // MySQL DATABASE 이름
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
			con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName,
				password);
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
			e.printStackTrace();
		}

		return con;
	}

}
