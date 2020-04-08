package chess.web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.chessGame.ChessCommand;
import chess.web.repository.JdbcChessConnection;

public class JdbcChessHistoryDao implements ChessHistoryDao {

	private static final String CHESS_HISTORY_TABLE = "chessHistory";
	private static final String MOVE_COMMAND = "move";

	@Override
	public boolean isChessHistoryTableExist() throws SQLException {
		String query = "SELECT * FROM information_schema.tables where table_name = ?";

		try (
			Connection connection = JdbcChessConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)
		) {
			preparedStatement.setString(1, CHESS_HISTORY_TABLE);
			return preparedStatement.executeQuery().next();
		}
	}

	@Override
	public void createChessHistory() throws SQLException {
		String query = "CREATE TABLE " + CHESS_HISTORY_TABLE + " ("
			+ "id bigint NOT NULL AUTO_INCREMENT,"
			+ "start varchar(3) NOT NULL,"
			+ "end varchar(3) NOT NULL,"
			+ "PRIMARY KEY (id)"
			+ ")";

		try (
			Connection connection = JdbcChessConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)
		) {
			preparedStatement.executeUpdate();
		}
	}

	@Override
	public List<ChessCommand> findAll() throws SQLException {
		String query = "SELECT * FROM " + CHESS_HISTORY_TABLE;

		try (
			Connection connection = JdbcChessConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet chessHistories = preparedStatement.executeQuery()
		) {
			List<ChessCommand> chessCommands = new ArrayList<>();
			addAllChessHistory(chessHistories, chessCommands);
			return chessCommands;
		}
	}

	private void addAllChessHistory(final ResultSet chessHistories, final List<ChessCommand> chessCommands) throws
		SQLException {
		while (chessHistories.next()) {
			chessCommands.add(ChessCommand.of(Arrays.asList(
				MOVE_COMMAND,
				chessHistories.getString("start"),
				chessHistories.getString("end"))));
		}
	}

	@Override
	public void addHistory(final String sourcePosition, final String targetPosition) throws SQLException {
		String query = "INSERT INTO " + CHESS_HISTORY_TABLE + " (start, end) VALUES (?, ?)";

		try (
			Connection connection = JdbcChessConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)
		) {
			preparedStatement.setString(1, sourcePosition);
			preparedStatement.setString(2, targetPosition);
			preparedStatement.executeUpdate();
		}
	}

	@Override
	public void deleteAll() throws SQLException {
		String query = "DELETE FROM " + CHESS_HISTORY_TABLE;

		try (
			Connection connection = JdbcChessConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query)
		) {
			preparedStatement.executeUpdate();
		}
	}

}
