package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.direction.Direction;

public abstract class Piece {

	public static final double PAWN_LOW_SCORE = 0.5;

	protected final Color color;

	protected Piece(Color color) {
		this.color = color;
	}

	public abstract Direction matchDirection(Position from, Position to);

	public boolean isSameColor(Piece piece) {
		return this.color == piece.color;
	}

	public boolean isSameColor(Color color) {
		return this.color == color;
	}

	public abstract boolean isPawn();

	public boolean isWhite() {
		return this.color == Color.WHITE;
	}

	public abstract boolean isKing();

	public abstract double getScore();
}
