package chess.web.dao;

import java.sql.SQLException;
import java.util.List;

import chess.domain.chessGame.ChessCommand;

public interface ChessHistoryDao {

	boolean isChessHistoryTableExist() throws SQLException;

	void createChessHistory() throws SQLException;

	List<ChessCommand> findAll() throws SQLException;

	void addHistory(String sourcePosition, String targetPosition) throws SQLException;

	void deleteAll() throws SQLException;

}
