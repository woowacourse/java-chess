package chess.service;

import java.sql.SQLException;

import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardInitializer;
import chess.domain.chessGame.ChessCommand;
import chess.domain.chessGame.ChessGame;
import chess.web.repository.dao.ChessHistoryDao;

public class ChessService {

	private final ChessHistoryDao chessHistoryDao;

	public ChessService() {
		this.chessHistoryDao = new ChessHistoryDao();
		initChessHistory();
	}

	private void initChessHistory() {
		try {
			createChessHistory();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createChessHistory() throws SQLException {
		if (!chessHistoryDao.isChessHistoryTableExist()) {
			chessHistoryDao.createChessHistory();
		}
	}

	public ChessGame loadChessGame() {
		ChessBoard chessBoard = new ChessBoard(ChessBoardInitializer.create());
		ChessGame chessGame = ChessGame.from(chessBoard);

		try {
			loadChessGameByChessHistory(chessGame);
			return chessGame;
		} catch (SQLException e) {
			throw new IllegalArgumentException(e.getMessage());
		}
	}

	private void loadChessGameByChessHistory(final ChessGame chessGame) throws SQLException {
		for (ChessCommand chessCommand : chessHistoryDao.findAll()) {
			chessGame.move(chessCommand);
		}
	}

	public void move(final String sourcePosition, final String targetPosition) {
		try {
			chessHistoryDao.addHistory(sourcePosition, targetPosition);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteChessGame() {
		try {
			chessHistoryDao.deleteAll();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
