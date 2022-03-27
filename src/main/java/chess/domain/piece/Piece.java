package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.direction.Direction;

public abstract class Piece {

	protected final Color color;

	protected Piece(Color color) {
		this.color = color;
	}

	public abstract Direction matchDirection(Position from, Position to);

	public abstract String getSymbol();

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

	public abstract double getScore();

	public abstract boolean isKing();
}
