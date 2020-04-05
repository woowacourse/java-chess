package chess.domain.web;

import java.sql.*;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class LogDao {
	public Connection getConnection() {
		Connection con = null;
		String server = "localhost:13306"; // MySQL 서버 주소
		String database = "db_name"; // MySQL DATABASE 이름
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
			con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
			System.out.println("정상적으로 연결되었습니다.");
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
			e.printStackTrace();
		}

		return con;
	}

	public void insert(String start, String end) throws SQLException {
		String query = "INSERT INTO log (start, end) VALUES (?, ?)";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.setString(1, start);
		pstmt.setString(2, end);
		pstmt.executeUpdate();
	}

	public Map<Integer, Log> selectAll() throws SQLException {
		String query = "SELECT * FROM log";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		Map<Integer, Log> result = new LinkedHashMap<>();
		while (rs.next()) {
			result.put(Integer.parseInt(rs.getString("log_id")), new Log(rs.getString("start"), rs.getString("end")));
		}
		return Collections.unmodifiableMap(result);
	}

	public void clear() throws SQLException {
		String query = "DELETE FROM log";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.executeUpdate();
		query = "ALTER TABLE log AUTO_INCREMENT=1";
		pstmt = getConnection().prepareStatement(query);
		pstmt.executeUpdate();
	}
}