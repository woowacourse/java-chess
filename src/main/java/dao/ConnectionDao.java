package dao;

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

	public Connection getConnection() {
		Connection connection = null;
		final String server = DatabaseInfo.SERVER; // MySQL 서버 주소
		final String database = DatabaseInfo.DATABASE; // MySQL DATABASE 이름
		final String option = DatabaseInfo.OPTION;
		final String userName = DatabaseInfo.USER_NAME; //  MySQL 서버 아이디
		final String password = DatabaseInfo.PASSWORD; // MySQL 서버 비밀번호
		final String protocol = DatabaseInfo.PROTOCOL;
		final String driver = DatabaseInfo.DRIVER;

		// 드라이버 로딩
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
			e.printStackTrace();
		}

		// 드라이버 연결
		try {
			connection = DriverManager.getConnection(
					protocol + server + SLASH + database + option, userName, password);
			System.out.println("정상적으로 연결되었습니다.");
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
			e.printStackTrace();
		}

		return connection;
	}
}
