package chess.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class MoveDto {
	private final RepositoryConnector connector;

	public MoveDto(RepositoryConnector connector) {
		this.connector = connector;
	}

	public void save(String source, String target, int gameId) throws SQLException {
		String query = "insert into move(game_id, source, target) values (?, ?, ?)";
		connector.executeUpdate(query, Integer.toString(gameId), source, target);
	}

	public Map<String, String> findMovesByGameId(int gameId) throws SQLException {
		String query = "select * from move where game_id = ?";
		ResultSet rs = connector.executeQuery(query, Integer.toString(gameId));
		Map<String, String> moves = new LinkedHashMap<>();
		while (rs.next()) {
			moves.put(rs.getString("source"), rs.getString("target"));
		}
		return moves;
	}
}
