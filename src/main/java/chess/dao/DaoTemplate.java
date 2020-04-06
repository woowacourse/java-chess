package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoTemplate {
	public Connection getConnection() {
		Connection connection = null;
		String address = "localhost:13306";
		String database = "db_name";
		String option = "?useSSL=false&serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
		String id = "root";
		String password = "root";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + address + "/" + database + option, id, password);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return connection;
	}

	public void closeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}
