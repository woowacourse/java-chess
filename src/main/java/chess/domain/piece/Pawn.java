package chess.domain.piece;

import static chess.domain.move.Direction.*;

import chess.domain.move.Direction;
import chess.domain.color.Color;

public final class Pawn extends Piece {

	private boolean isFirst = true;

	public Pawn(final Color color) {
		super(color);
	}

	@Override
	public String name() {
		String name = "p";
		if (super.team().equals(Color.WHITE)) {
			return name;
		}
		return name.toUpperCase();
	}

	@Override
	public boolean movable(final Direction direction) {
		if (name().equals(name().toUpperCase())) {
			return DOWN.equals(direction);
		}
		return UP.equals(direction);
	}

	@Override
	public boolean movableByCount(final int count) {
		if (isFirst) {
			isFirst = false;
			return count <= 1;
		}
		return count == 0;
	}
}
