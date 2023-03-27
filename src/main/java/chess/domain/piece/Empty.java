package chess.domain.piece;

import static chess.domain.piece.PieceType.*;

import java.util.Set;

import chess.domain.color.Color;
import chess.domain.move.Direction;
import chess.domain.position.Position;

public final class Empty extends Piece {
	private static final String name = ".";

	public Empty(final Color color, final Position position) {
		super(color, position);
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public Set<Direction> direction() {
		return Set.of();
	}

	@Override
	public boolean isEmpty() {
		return true;
	}

	@Override
	public boolean movable(final Direction direction) {
		return false;
	}

	@Override
	public boolean movableByCount(final int count) {
		return false;
	}

	@Override
	public PieceType type() {
		return EMPTY;
	}
}
