package chess.repository.dao;

import java.util.Collections;
import java.util.List;

import chess.repository.entity.GameEntity;

public class GameDao {

	private final JdbcTemplate jdbcTemplate;

	public GameDao() {
		this.jdbcTemplate = new JdbcTemplate();
	}

	public GameEntity save() {
		final String query = "INSERT INTO game(id) VALUES(DEFAULT)";
		final List<Object> parameters = Collections.emptyList();
		long gameId = jdbcTemplate.executeUpdate(query, parameters);
		return new GameEntity(gameId);
	}

	public void deleteById(long gameId) {
		final String query = "DELETE FROM game WHERE id = (?)";
		final List<Object> parameters = List.of(gameId);
		jdbcTemplate.executeUpdateForDelete(query, parameters);
	}
}
