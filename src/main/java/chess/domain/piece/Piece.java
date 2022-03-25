package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public abstract class Piece {

	protected final Color color;

	protected Piece(Color color) {
		this.color = color;
	}

	public abstract boolean isMovable(Position from, Position to);

	public abstract String getSymbol();

	public boolean isSameColor(Piece piece) {
		return this.color == piece.color;
	}

	public abstract boolean isPawn();
}
