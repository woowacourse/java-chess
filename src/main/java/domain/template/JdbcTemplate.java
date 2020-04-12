package domain.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class JdbcTemplate {
	private static final String SERVER = "localhost:3306";// MySQL 서버 주소
	private static final String DATABASE = "board";// MySQL DATABASE 이름
	private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
	private static final String USER_NAME = "root";//  MySQL 서버 아이디
	private static final String PASSWORD = "1234";// MySQL 서버 비밀번호
	public static final String URL = String.format("jdbc:mysql://%s/%s%s", SERVER, DATABASE, OPTION);

	public Connection getConnection() {
		Connection con = null;

		// 드라이버 로딩
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(String.format(" !! JDBC Driver load 오류: %s", e.getMessage()));
		}

		// 드라이버 연결
		try {
			System.out.println("정상적으로 연결되었습니다.");
			con = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			System.err.println(String.format("연결 오류:%s", e.getMessage()));
		}
		return con;
	}

	public void executeUpdate(String query, PreparedStatementSetter pss) throws SQLException {
		try (Connection connection = getConnection();
			 PreparedStatement pstmt = connection.prepareStatement(query)) {
			pss.setParameters(pstmt);
			pstmt.executeUpdate();
		}
	}

	public <T> List<T> executeQuery(String query, PreparedStatementSetter pss, RowMapper<T> rowMapper) throws
		SQLException {
		List<T> items = new ArrayList<>();
		try (Connection connection = getConnection();
			 PreparedStatement pstmt = connection.prepareStatement(query);
			 ResultSet rs = pstmt.executeQuery()) {
			pss.setParameters(pstmt);
			while (rs.next()) {
				if (rowMapper.rowMap(rs) != null) {
					items.add(rowMapper.rowMap(rs));
				}
			}
			return items;
		}
	}
}
