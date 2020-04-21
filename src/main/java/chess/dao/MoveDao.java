package chess.dao;

import chess.domain.position.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MoveDao {
	public void save(Position source, Position target, int gameId) throws SQLException, ClassNotFoundException {
		String query = "insert into move(game_id, source, target) values (?, ?, ?)";
		try (Connection con = ConnectionLoader.load(); PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setInt(1, gameId);
			pstmt.setString(2, source.getName());
			pstmt.setString(3, target.getName());
			pstmt.executeUpdate();
		}
	}

	public Map<Integer, List<String>> findMovesByGameId(int gameId) throws SQLException, ClassNotFoundException {
		ResultSet rs = null;
		String query = "select * from move where game_id = ?";
		try (Connection con = ConnectionLoader.load(); PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setInt(1, gameId);
			rs = pstmt.executeQuery();
			Map<Integer, List<String>> moves = new LinkedHashMap<>();
			while (rs.next()) {
				moves.put(rs.getInt("id"), Arrays.asList(rs.getString("source"), rs.getString("target")));
			}
			return moves;
		} finally {
			if (Objects.nonNull(rs)) {
				rs.close();
			}
		}
	}
}
