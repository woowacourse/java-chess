package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.entity.ChessGameEntity;
import chess.web.repository.JdbcTemplate;
import chess.web.repository.MySqlConnectionManager;

class MySqlChessGameDaoTest {

	private static final String CHESS_GAME_TABLE = "chess_games";
	private static final long INIT_AUTO_INCREMENT = 1L;

	private JdbcTemplate jdbcTemplate;

	@BeforeEach
	void setUp() {
		jdbcTemplate = new JdbcTemplate(MySqlConnectionManager.getInstance());

		final String query1 = "DELETE FROM " + CHESS_GAME_TABLE;
		jdbcTemplate.executeUpdate(query1);

		final String query2 = "ALTER TABLE " + CHESS_GAME_TABLE + " AUTO_INCREMENT = ?";
		jdbcTemplate.executeUpdate(query2, preparedStatement -> preparedStatement.setLong(1, INIT_AUTO_INCREMENT));
	}

	@ParameterizedTest
	@NullSource
	void MySqlChessGameDao_NullJdbcTemplate_ExceptionThrown(final JdbcTemplate jdbcTemplate) {
		assertThatThrownBy(() -> new MySqlChessGameDao(jdbcTemplate))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("JdbcTemplate이 null입니다.");
	}

	@Test
	void MySqlChessGameDao_JdbcTemplate_GenerateInstance() {
		assertThat(new MySqlChessGameDao(jdbcTemplate)).isInstanceOf(MySqlChessGameDao.class);
	}

	@ParameterizedTest
	@NullSource
	void add_NullChessGameEntity_ExceptionThrown(final ChessGameEntity entity) {
		final MySqlChessGameDao mySqlChessGameDao = new MySqlChessGameDao(jdbcTemplate);

		assertThatThrownBy(() -> mySqlChessGameDao.add(entity))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("엔티티가 null입니다.");
	}

	@Test
	void add_ChessGameEntity_InsertChessGame() {
		final MySqlChessGameDao mySqlChessGameDao = new MySqlChessGameDao(jdbcTemplate);
		final ChessGameEntity entity = ChessGameEntity.of(LocalDateTime.now());

		assertThat(mySqlChessGameDao.add(entity)).isEqualTo(INIT_AUTO_INCREMENT);
	}

	@Test
	void findMaxGameId_EmptyChessGame_ExceptionThrown() {
		final MySqlChessGameDao mySqlChessGameDao = new MySqlChessGameDao(jdbcTemplate);

		assertThat(mySqlChessGameDao.findMaxGameId()).isEqualTo(MySqlChessGameDao.EMPTY_CHESS_GAME);
	}

	@Test
	void findMaxGameId_ReturnMaxGameId() {
		final MySqlChessGameDao mySqlChessGameDao = new MySqlChessGameDao(jdbcTemplate);
		final ChessGameEntity entity = ChessGameEntity.of(LocalDateTime.now());
		mySqlChessGameDao.add(entity);

		assertThat(mySqlChessGameDao.findMaxGameId()).isEqualTo(INIT_AUTO_INCREMENT);
	}

	@Test
	void isEmpty_EmptyChessGame_ReturnTrue() {
		final MySqlChessGameDao mySqlChessGameDao = new MySqlChessGameDao(jdbcTemplate);

		assertThat(mySqlChessGameDao.isEmpty()).isTrue();
	}

	@Test
	void isEmpty_NotEmptyChessGame_ReturnFalse() {
		final MySqlChessGameDao mySqlChessGameDao = new MySqlChessGameDao(jdbcTemplate);
		final ChessGameEntity entity = ChessGameEntity.of(LocalDateTime.now());
		mySqlChessGameDao.add(entity);

		assertThat(mySqlChessGameDao.isEmpty()).isFalse();
	}

	@Test
	void deleteAll_ExistChessGame_DeleteAllFromChessGameTable() {
		final MySqlChessGameDao mySqlChessGameDao = new MySqlChessGameDao(jdbcTemplate);
		final ChessGameEntity entity = ChessGameEntity.of(LocalDateTime.now());
		mySqlChessGameDao.add(entity);
		mySqlChessGameDao.deleteAll();

		assertThat(findAll().isEmpty()).isTrue();
	}

	private List<ChessGameEntity> findAll() {
		final String query = "SELECT * FROM " + CHESS_GAME_TABLE;

		return jdbcTemplate.executeQuery(query, resultSet -> {
			final List<ChessGameEntity> entities = new ArrayList<>();

			while (resultSet.next()) {
				entities.add(ChessGameEntity.of(
					resultSet.getLong("game_id"),
					resultSet.getTimestamp("created_time").toLocalDateTime()));
			}
			return entities;
		});
	}

	@AfterEach
	void tearDown() {
		final String query = "DELETE FROM " + CHESS_GAME_TABLE;

		jdbcTemplate.executeUpdate(query);
	}

}