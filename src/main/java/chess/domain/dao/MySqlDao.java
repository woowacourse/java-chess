package chess.domain.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDao implements Connectable {
	@Override
	public Connection connect() {
		Connection con = null;
		String server = "localhost:13306";
		String database = "chess_game";
		String option = "?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8";
		String userName = "root";
		String password = "root";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC Driver load 오류" + e.getMessage());
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
			System.out.println("정상적으로 연결되었습니다.");
		} catch (SQLException e) {
			System.err.println("연결 오류" + e.getMessage());
			e.printStackTrace();
		}

		return con;
	}

	@Override
	public void close(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.err.println("con 오류:" + e.getMessage());
		}
	}


}
