package chess.domain.piece;

import static chess.domain.move.Direction.*;

import chess.domain.move.Direction;
import chess.domain.team.Team;

public final class Pawn extends Piece {

	private boolean isFirst = true;

	public Pawn(final Team team) {
		super(team);
	}

	@Override
	public String name() {
		String name = "p";
		if (super.team().equals(Team.WHITE)) {
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
