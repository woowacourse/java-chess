package chess.domain.piece;

import chess.domain.color.Color;
import chess.domain.move.Direction;
import chess.domain.position.Position;

public final class Empty extends Piece {

	public Empty(final Color color, final Position position) {
		super(color, position);
	}

	@Override
	public String name() {
		return ".";
	}

	@Override
	public boolean movable(final Direction direction) {
		return false;
	}

	@Override
	public boolean movableByCount(final int count) {
		return false;
	}
}
