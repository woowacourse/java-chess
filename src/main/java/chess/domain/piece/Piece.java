package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
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

	public abstract boolean isPawn();

	public boolean isWhite() {
		return this.color == Color.WHITE;
	}
}
