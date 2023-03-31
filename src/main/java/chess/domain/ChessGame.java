package chess.domain;

import chess.dao.ChessBoardDao;
import chess.dao.ChessDao;

public class ChessGame {
	private static final ChessDao chessDao = new ChessBoardDao();

	public static Board board() {
		Board board = chessDao.find();
		if (board == null) {
			board = Board.create();
			chessDao.save(board);
		}
		return board;
	}

	public static void initializeBoard() {
		chessDao.init();
	}

	public static void update() {
		chessDao.update(board());
	}
}
