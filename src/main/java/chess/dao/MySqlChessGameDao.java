package chess.dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Objects;

import chess.entity.ChessGameEntity;
import chess.web.repository.JdbcTemplate;

public class MySqlChessGameDao implements ChessGameDao {

	public static final long EMPTY_CHESS_GAME = 0L;
	private static final String CHESS_GAME_TABLE = "chess_games";

	private final JdbcTemplate jdbcTemplate;

	public MySqlChessGameDao(final JdbcTemplate jdbcTemplate) {
		Objects.requireNonNull(jdbcTemplate, "JdbcTemplate이 null입니다.");
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public long add(final ChessGameEntity entity) {
		Objects.requireNonNull(entity, "엔티티가 null입니다.");
		final String query = "INSERT INTO " + CHESS_GAME_TABLE + " (created_time) VALUES (?)";

		return jdbcTemplate.executeUpdate(query, preparedStatement -> {
			preparedStatement.setTimestamp(1, Timestamp.valueOf(entity.getCreatedTime()));
		});
	}

	@Override
	public long findMaxGameId() {
		final String query = "SELECT MAX(game_id) AS max_id FROM " + CHESS_GAME_TABLE;

		return jdbcTemplate.executeQuery(query, resultSet -> {
			if (resultSet.next()) {
				return resultSet.getLong("max_id");
			}
			return EMPTY_CHESS_GAME;
		});
	}

	@Override
	public boolean isEmpty() {
		final String query = "SELECT * FROM " + CHESS_GAME_TABLE;

		return !jdbcTemplate.executeQuery(query, ResultSet::next);
	}

	@Override
	public void deleteAll() {
		final String query = "DELETE FROM " + CHESS_GAME_TABLE;

		jdbcTemplate.executeUpdate(query);
	}

}
