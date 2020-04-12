package dao;

import dao.exceptions.ConnectionDaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {
	private static final ConnectionDao CONNECTION_DAO;
	private static final String SLASH = "/";

	static {
		CONNECTION_DAO = new ConnectionDao();
	}

	private ConnectionDao() {
	}

	public static ConnectionDao getInstance() {
		return CONNECTION_DAO;
	}

	public Connection getConnection() throws SQLException {
		Connection connection = null;
		final String server = DatabaseInfo.SERVER; // MySQL 서버 주소
		final String database = DatabaseInfo.DATABASE; // MySQL DATABASE 이름
		final String option = DatabaseInfo.OPTION;
		final String userName = DatabaseInfo.USER_NAME; //  MySQL 서버 아이디
		final String password = DatabaseInfo.PASSWORD; // MySQL 서버 비밀번호
		final String protocol = DatabaseInfo.PROTOCOL;
		final String driver = DatabaseInfo.DRIVER;

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new ConnectionDaoException(" !! JDBC Driver load 오류: " + e.getMessage());
		}

		connection = DriverManager.getConnection(
				protocol + server + SLASH + database + option, userName, password);
		System.out.println("데이터베이스에 정상적으로 연결되었습니다.");

		return connection;
	}
}
