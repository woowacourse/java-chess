package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class GamesDao {
	public int createGame(String firstUser, String secondUser) throws SQLException {
		String query = "insert into games(user1, user2) values (?, ?)";
		PreparedStatement pstmt = ConnectionLoader.load().prepareStatement(query, new String[] {"id"});
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

	public Map<String, String> everyGames() throws SQLException {
		Map<String, String> games = new HashMap<>();
		String query = "select * from games";
		PreparedStatement pstmt = ConnectionLoader.load().prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			games.put(rs.getString("user1"), rs.getString("user2"));
		}
		return games;
	}
}
