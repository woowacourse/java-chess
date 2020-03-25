package chess.domain;

import java.util.Map;

import chess.domain.piece.Piece;

public class Board {
	private final Map<String, Piece> board;
	private final Map<String, Piece> reversedBoard;

	public Board(Map<String, Piece> board) {
		this.board = Map.copyOf(board);
		reversedBoard = Map.copyOf(board);
	}
}
