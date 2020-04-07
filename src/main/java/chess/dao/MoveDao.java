package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MoveDao {
	public void save(String source, String target, int gameId) throws SQLException {
		String query = "insert into move(game_id, source, target) values (?, ?, ?)";
		PreparedStatement pstmt = ConnectionLoader.load().prepareStatement(query);
		pstmt.setInt(1, gameId);
		pstmt.setString(2, source);
		pstmt.setString(3, target);
		pstmt.executeUpdate();
	}

	public Map<Integer, List<String>> findMovesByGameId(int gameId) throws SQLException {
		String query = "select * from move where game_id = ?";
		PreparedStatement pstmt = ConnectionLoader.load().prepareStatement(query);
		pstmt.setInt(1, gameId);
		ResultSet rs = pstmt.executeQuery();
		Map<Integer, List<String>> moves = new LinkedHashMap<>();
		while (rs.next()) {
			moves.put(rs.getInt("id"), Arrays.asList(rs.getString("source"), rs.getString("target")));
		}
		return moves;
	}
}
