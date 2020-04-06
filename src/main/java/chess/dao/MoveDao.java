package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MoveDao {
	private static Connection getConnection() {
		Connection con = null;
		String server = "127.0.0.1:13306"; // MySQL 서버 주소
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
		} catch (SQLException e) {
			System.err.println("연결 오류:" + e.getMessage());
			e.printStackTrace();
		}
		return con;
	}

	public void save(String source, String target, int gameId) throws SQLException {
		String query = "insert into move(game_id, source, target) values (?, ?, ?)";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.setInt(1, gameId);
		pstmt.setString(2, source);
		pstmt.setString(3, target);
		pstmt.executeUpdate();
	}

	public Map<String, String> findMovesByGameId(int gameId) throws SQLException {
		String query = "select * from move where game_id = ?";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		pstmt.setInt(1, gameId);
		ResultSet rs = pstmt.executeQuery();
		Map<String, String> moves = new LinkedHashMap<>();
		while (rs.next()) {
			moves.put(rs.getString("source"), rs.getString("target"));
		}
		return moves;
	}
}
