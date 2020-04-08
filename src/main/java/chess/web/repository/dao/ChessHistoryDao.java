package chess.web.repository.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.chessGame.ChessCommand;
import chess.web.repository.JdbcChessConnection;

public class ChessHistoryDao {

	private static final String CHESS_HISTORY_TABLE = "chessHistory";
	private static final String MOVE_COMMAND = "move";

	public boolean isChessHistoryTableExist() throws SQLException {
		String query = "SELECT * FROM information_schema.tables where table_name = ?";
		return JdbcChessConnection.executeQuery(query, CHESS_HISTORY_TABLE).next();
	}

	public void createChessHistory() throws SQLException {
		String query = "CREATE TABLE " + CHESS_HISTORY_TABLE + " ("
			+ "start varchar(3) NOT NULL,"
			+ "end varchar(3) NOT NULL"
			+ ")";
		JdbcChessConnection.executeUpdate(query);
	}

	public List<ChessCommand> findAll() throws SQLException {
		String query = "SELECT * FROM " + CHESS_HISTORY_TABLE;
		ResultSet chessHistories = JdbcChessConnection.executeQuery(query);
		List<ChessCommand> chessCommands = new ArrayList<>();

		while (chessHistories.next()) {
			chessCommands.add(ChessCommand.of(Arrays.asList(
				MOVE_COMMAND,
				chessHistories.getString("start"),
				chessHistories.getString("end"))));
		}
		return chessCommands;
	}

	public void addHistory(final String sourcePosition, final String targetPosition) throws SQLException {
		String query = "INSERT INTO " + CHESS_HISTORY_TABLE + " (start, end) VALUES (?, ?)";
		JdbcChessConnection.executeUpdate(query, sourcePosition, targetPosition);
	}

	public void deleteAll() throws SQLException {
		String query = "DELETE FROM " + CHESS_HISTORY_TABLE;
		JdbcChessConnection.executeUpdate(query);
	}

}
