package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import view.OutputView;

public class ConnectionManager {
	private static final String SERVER = "localhost:13306";
	private static final String DATABASE = "chess2";
	private static final String OPTION = "?useSSL=false&serverTimezone=UTC";//  MySQL 서버 아이디
	private static final String USER_NAME = "root";// MySQL 서버 비밀번호
	private static final String PASSWORD = "root";

	private ConnectionManager() {

	}

	public static Connection getConnection() {
		Connection con = null;

		// 드라이버 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
			OutputView.printMessage(e);
		}

		// 드라이버 연결
		try {
			con = DriverManager.getConnection(
				String.format("jdbc:mysql://%s/%s%s", SERVER, DATABASE, OPTION), USER_NAME, PASSWORD);
			System.out.println("정상적으로 연결되었습니다.");
		} catch (SQLException e) {
			OutputView.printMessage(e);
		}

		return con;
	}

	// 드라이버 연결해제
	public static void closeConnection(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.err.println("con 오류:" + e.getMessage());
		}
	}
}
