package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public abstract class Piece {

	private final Color color;
	private final Position position;

	protected Piece(Color color, Position position) {
		this.color = color;
		this.position = position;
	}

	public boolean isSamePosition(int row, int column) {
		return this.position.equals(new Position(row, column));
	}

	public abstract String getSymbol();
}
