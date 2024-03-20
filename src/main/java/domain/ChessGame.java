package domain;

import java.util.Collections;
import java.util.List;

public class ChessGame {
	private final ChessBoard chessBoard;

	public ChessGame() {
		this.chessBoard = new ChessBoard();
	}

	public List<Piece> move(Position from, Position to) {
		boolean move = chessBoard.move(from, to);
		if (move) {
			return getPiecesOnBoard();
		}
		return Collections.emptyList();
	}

	public List<Piece> getPiecesOnBoard() {
		return chessBoard.getPiecesOnBoard();
	}
}
