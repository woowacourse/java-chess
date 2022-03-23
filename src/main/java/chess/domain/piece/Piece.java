package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public abstract class Piece {

	protected final Color color;
	protected Position position;

	protected Piece(Color color, Position position) {
		this.color = color;
		this.position = position;
	}

	public boolean isSamePosition(int row, int column) {
		return this.position.equals(new Position(row, column));
	}

	public abstract void move(int row, int column);

	public abstract String getSymbol();
}
