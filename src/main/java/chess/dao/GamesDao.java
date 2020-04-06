package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GamesDao {
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

	public int createGame(String firstUser, String secondUser) throws SQLException {
		String query = "insert into games(user1, user2) values (?, ?)";
		PreparedStatement pstmt = getConnection().prepareStatement(query, new String[] {"id"});
		pstmt.setString(1, firstUser);
		pstmt.setString(2, secondUser);
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (!rs.next()) {
			throw new IllegalArgumentException();
		}
		int id = rs.getInt(1);
		return id;
	}

	public Map<String, Object> everyGames() throws SQLException {
		Map<String, Object> games = new HashMap<>();
		String query = "select * from games";
		PreparedStatement pstmt = getConnection().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			games.put(rs.getString("user1"), rs.getString("user2"));
		}
		return games;
	}
}
