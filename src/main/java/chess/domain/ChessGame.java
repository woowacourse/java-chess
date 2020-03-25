package chess.domain;

import chess.domain.board.PiecesFactory;
import chess.domain.board.position.Position;
import chess.domain.piece.Color;

public class ChessGame {
	private final Pieces pieces;
	private Color color;

	public ChessGame() {
		pieces = PiecesFactory.of();
		color = Color.WHITE;
	}

	public void move(Position start, Position end) {
		pieces.move(start, end, color);
		color = color.changeColor();
	}

	public Pieces getPieces() {
		return pieces;
	}
}
