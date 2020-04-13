package chess.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {
	private static final String server = "localhost:13306";
	private static final String database = "chess";
	private static final String option = "?useSSL=false&serverTimezone=UTC";
	private static final String userName = "root";
	private static final String password = "root";

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("!! JDBC Driver load 오류 : " + e.getMessage());
			e.printStackTrace();
		}

		Connection con = null;

		try {
			con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
			System.out.println("정상적으로 연결됐습니다.");
		} catch (SQLException e) {
			System.err.println("연결 오류 :" + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}

	public static void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.err.println("con 오류 : " + e.getMessage());
		}
	}
}
