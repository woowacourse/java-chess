package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
	private static final String SERVER = "localhost:3306"; // MySQL 서버 주소
	private static final String DATABASE = "chess_game"; // MySQL DATABASE 이름
	private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
	private static final String USER_NAME = "root"; //  MySQL 서버 아이디
	private static final String PASSWORD = "root"; // MySQL 서버 비밀번호
	private static final String URL = String.format("jdbc:mysql://%s/%s%s", SERVER, DATABASE, OPTION);

	private Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
		}
		try {
			conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			System.out.println("정상적으로 연결되었습니다.");
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
		}
		return conn;
	}

	public <T> T executeUpdate(String query, PreparedStatementSetter setter, RowMapper<T> mapper) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			setter.set(pstmt);
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			return mapper.map(rs);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public <T> T executeQuery(String query, PreparedStatementSetter setter, RowMapper<T> mapper) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(query);
			setter.set(pstmt);
			rs = pstmt.executeQuery();
			return mapper.map(rs);
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
}
