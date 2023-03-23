package chess.domain.piece;

import chess.domain.color.Color;
import chess.domain.move.Direction;
import chess.domain.position.Position;

public abstract class Piece {

	private final Color color;
	private final Position position;

	protected Piece(final Color color, final Position position) {
		this.color = color;
		this.position = position;
	}

	public abstract String name();

	public abstract boolean movable(final Direction direction);

	public abstract boolean movableByCount(final int count);

	public boolean isSameTeam(final Color color) {
		return this.color.equals(color);
	}

	public Color color() {
		return color;
	}
}
