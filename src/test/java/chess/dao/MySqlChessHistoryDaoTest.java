package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.entity.ChessHistoryEntity;
import chess.web.repository.JdbcTemplate;
import chess.web.repository.MySqlConnectionManager;

class MySqlChessHistoryDaoTest {

	private static final String CHESS_HISTORY_TABLE = "chess_histories";
	private static final long INIT_AUTO_INCREMENT = 1L;

	private JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setUp() {
		jdbcTemplate = new JdbcTemplate(MySqlConnectionManager.getInstance());

		final String query1 = "DELETE FROM " + CHESS_HISTORY_TABLE;
		jdbcTemplate.executeUpdate(query1);

		final String query2 = "ALTER TABLE " + CHESS_HISTORY_TABLE + " AUTO_INCREMENT = ?";
		jdbcTemplate.executeUpdate(query2, preparedStatement -> preparedStatement.setLong(1, INIT_AUTO_INCREMENT));
	}

	@ParameterizedTest
	@NullSource
	void MySqlChessHistoryDao_NullJdbcTemplate_ExceptionThrown(final JdbcTemplate jdbcTemplate) {
		assertThatThrownBy(() -> new MySqlChessHistoryDao(jdbcTemplate))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("JdbcTemplate이 null입니다.");
	}

	@Test
	void MySqlChessHistoryDao_JdbcTemplate_GenerateInstance() {
		assertThat(new MySqlChessHistoryDao(jdbcTemplate)).isInstanceOf(MySqlChessHistoryDao.class);
	}

	@Test
	void findAllByGameId_GameId_ReturnChessHistoryEntities() {
		final MySqlChessHistoryDao mySqlChessHistoryDao = new MySqlChessHistoryDao(jdbcTemplate);
		final long gameId = 1;
		final ChessHistoryEntity value = ChessHistoryEntity.of(gameId, "b2", "b4", LocalDateTime.now());
		mySqlChessHistoryDao.add(value);

		assertThat(isSame(mySqlChessHistoryDao.findAllByGameId(gameId), value)).isTrue();
	}

	private boolean isSame(final List<ChessHistoryEntity> actual, final ChessHistoryEntity expected) {
		return actual.size() == 1
			&& actual.get(0).getGameId() == expected.getGameId()
			&& actual.get(0).getStart().equals(expected.getStart())
			&& actual.get(0).getEnd().equals(expected.getEnd());
	}

	@ParameterizedTest
	@NullSource
	void add_NullChessHistoryEntity_ExceptionThrown(final ChessHistoryEntity entity) {
		final MySqlChessHistoryDao mySqlChessHistoryDao = new MySqlChessHistoryDao(jdbcTemplate);

		assertThatThrownBy(() -> mySqlChessHistoryDao.add(entity))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("엔티티가 null입니다.");
	}

	@Test
	void add_ChessHistoryEntity_InsertChessHistory() {
		final MySqlChessHistoryDao mySqlChessHistoryDao = new MySqlChessHistoryDao(jdbcTemplate);
		final long gameId = 1;
		mySqlChessHistoryDao.add(ChessHistoryEntity.of(gameId, "b2", "b4", LocalDateTime.now()));

		assertThat(isChessHistoryExist("b2", "b4")).isTrue();
	}

	private boolean isChessHistoryExist(final String start, final String end) {
		final String query = "SELECT * FROM " + CHESS_HISTORY_TABLE + " WHERE start = ? AND end = ?";

		return jdbcTemplate.executeQuery(query, ResultSet::next, preparedStatement -> {
			preparedStatement.setString(1, start);
			preparedStatement.setString(2, end);
		});
	}

	@Test
	void deleteAll_DeleteAllChessHistoryFromChessHistoryTable() {
		final MySqlChessHistoryDao mySqlChessHistoryDao = new MySqlChessHistoryDao(jdbcTemplate);
		mySqlChessHistoryDao.add(ChessHistoryEntity.of("b2", "b4"));
		mySqlChessHistoryDao.deleteAll();

		assertThat(findAll().isEmpty()).isTrue();
	}

	private List<ChessHistoryEntity> findAll() {
		final String query = "SELECT * FROM " + CHESS_HISTORY_TABLE;

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
		});
	}

	@AfterEach
	void tearDown() {
		final String query = "DELETE FROM " + CHESS_HISTORY_TABLE;

		jdbcTemplate.executeUpdate(query);
	}

}