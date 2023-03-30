package chess.repository.dao;

import java.util.Collections;
import java.util.List;

public class GameDao {

	private final JdbcTemplate jdbcTemplate;

	public GameDao() {
		this.jdbcTemplate = new JdbcTemplate();
	}

	public long save() {
		final String query = "INSERT INTO game(id) VALUES(DEFAULT)";
		final List<Object> parameters = Collections.emptyList();
		return jdbcTemplate.executeUpdate(query, parameters);
	}

	public void deleteById(long gameId) {
		final String query = "DELETE FROM game WHERE id = (?)";
		final List<Object> parameters = List.of(gameId);
		jdbcTemplate.executeUpdateForDelete(query, parameters);
	}
}
