package chess.web.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.chessGame.ChessCommand;
import chess.web.repository.JdbcChessConnection;

class JdbcChessHistoryDaoTest {

	private static final String CHESS_HISTORY_TABLE = "chessHistory";
	private static final String MOVE_COMMAND = "move";

	@BeforeEach
	void setUp() {
		String query = "DROP TABLE " + CHESS_HISTORY_TABLE;

		try (
			Connection connection = JdbcChessConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)
		) {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	void isChessHistoryTableExist_ChessHistoryTableNotExist_ReturnFalse() throws SQLException {
		JdbcChessHistoryDao jdbcChessHistoryDao = new JdbcChessHistoryDao();

		assertThat(jdbcChessHistoryDao.isChessHistoryTableExist()).isFalse();
	}

	@Test
	void createChessHistory_CreateChessHistory() throws SQLException {
		JdbcChessHistoryDao jdbcChessHistoryDao = new JdbcChessHistoryDao();
		jdbcChessHistoryDao.createChessHistory();

		assertThat(jdbcChessHistoryDao.isChessHistoryTableExist()).isTrue();
	}

	@Test
	void findAll_EmptyChessHistory_ReturnEmptyChessHistory() throws SQLException {
		JdbcChessHistoryDao jdbcChessHistoryDao = new JdbcChessHistoryDao();
		jdbcChessHistoryDao.createChessHistory();

		assertThat(jdbcChessHistoryDao.findAll().isEmpty()).isTrue();
	}

	@Test
	void findAll_ChessHistory_ReturnAllChessHistoryByChessCommand() throws SQLException {
		JdbcChessHistoryDao jdbcChessHistoryDao = new JdbcChessHistoryDao();
		jdbcChessHistoryDao.createChessHistory();
		jdbcChessHistoryDao.addHistory("b2", "b4");
		jdbcChessHistoryDao.addHistory("b7", "b5");

		List<ChessCommand> expected = Arrays.asList(
			ChessCommand.of(Arrays.asList(MOVE_COMMAND, "b2", "b4")),
			ChessCommand.of(Arrays.asList(MOVE_COMMAND, "b7", "b5")));
		assertThat(jdbcChessHistoryDao.findAll()).isEqualTo(expected);
	}

	@Test
	void addHistory_SourcePositionAndTargetPosition_AddChessHistoryIntoDB() throws SQLException {
		JdbcChessHistoryDao jdbcChessHistoryDao = new JdbcChessHistoryDao();
		jdbcChessHistoryDao.createChessHistory();
		jdbcChessHistoryDao.addHistory("b2", "b4");

		assertThat(isChessHistoryExist("b2", "b4")).isTrue();
	}

	@Test
	void deleteAll_RemoveAllChessHistory() throws SQLException {
		JdbcChessHistoryDao jdbcChessHistoryDao = new JdbcChessHistoryDao();
		jdbcChessHistoryDao.createChessHistory();
		jdbcChessHistoryDao.addHistory("b2", "b4");
		jdbcChessHistoryDao.deleteAll();

		assertThat(jdbcChessHistoryDao.findAll().isEmpty()).isTrue();
	}

	@AfterEach
	void tearDown() {
		String query = "DROP TABLE " + CHESS_HISTORY_TABLE;

		try (
			Connection connection = JdbcChessConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)
		) {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private boolean isChessHistoryExist(final String sourcePosition, final String targetPosition) {
		String query = "SELECT * FROM " + CHESS_HISTORY_TABLE + " WHERE start = ? AND end = ?";

		try (
			Connection connection = JdbcChessConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
		) {
			preparedStatement.setString(1, sourcePosition);
			preparedStatement.setString(2, targetPosition);
			return preparedStatement.executeQuery().next();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

}