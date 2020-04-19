package chess.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import chess.entity.ChessHistoryEntity;
import chess.web.repository.JdbcTemplate;

public class MySqlChessHistoryDao implements ChessHistoryDao {

	private static final String CHESS_HISTORY_TABLE = "chess_histories";

	private final JdbcTemplate jdbcTemplate;

	public MySqlChessHistoryDao(final JdbcTemplate jdbcTemplate) {
		Objects.requireNonNull(jdbcTemplate, "JdbcTemplate이 null입니다.");
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<ChessHistoryEntity> findAllByGameId(final long gameId) {
		final String query = "SELECT * FROM " + CHESS_HISTORY_TABLE + " WHERE game_id = ?";

		return jdbcTemplate.executeQuery(query, resultSet -> {
			final List<ChessHistoryEntity> entities = new ArrayList<>();

			while (resultSet.next()) {
				entities.add(ChessHistoryEntity.of(
					resultSet.getLong("history_id"),
					resultSet.getLong("game_id"),
					resultSet.getString("start"),
					resultSet.getString("end"),
					resultSet.getTimestamp("created_time").toLocalDateTime()));
			}
			return entities;
		}, preparedStatement -> preparedStatement.setLong(1, gameId));
	}

	@Override
	public void add(final ChessHistoryEntity entity) {
		Objects.requireNonNull(entity, "엔티티가 null입니다.");
		final String query =
			"INSERT INTO " + CHESS_HISTORY_TABLE + " (game_id, start, end, created_time) VALUES (?, ?, ?, ?)";

		jdbcTemplate.executeUpdate(query, preparedStatement -> {
			preparedStatement.setLong(1, entity.getGameId());
			preparedStatement.setString(2, entity.getStart());
			preparedStatement.setString(3, entity.getEnd());
			preparedStatement.setTimestamp(4, Timestamp.valueOf(entity.getCreatedTime()));
		});
	}

	@Override
	public void deleteAll() {
		final String query = "DELETE FROM " + CHESS_HISTORY_TABLE;

		jdbcTemplate.executeUpdate(query);
	}

}
