package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.direction.Direction;

public abstract class Piece {

	public static final double PAWN_LOW_SCORE = 0.5;

	protected final Color color;

	protected Piece(Color color) {
		this.color = color;
	}

	public abstract Direction checkMovableRange(Position from, Position to);

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

	public boolean isMovable(Position from, Position to) {
		try {
			checkMovableRange(from, to);
			return true;
		} catch (IllegalArgumentException exception) {
			return false;
		}
	}

	public boolean isMovable(Position from, Position to, Piece target) {
		return !this.isSameColor(target);
	}
}
