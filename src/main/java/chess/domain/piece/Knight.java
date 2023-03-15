package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.RelativePosition;
import chess.domain.Team;

public class Knight implements Piece {

	private final Team team;
	private final Movement movement;

	public Knight(final Team team) {
		this.team = team;
		this.movement = Movement.KNIGHT;
	}

	@Override
	public boolean isMobile(final RelativePosition relativePosition) {
		return movement.isMobile(relativePosition);
	}

	@Override
	public boolean isBlack() {
		return team.isBlack();
	}

	@Override
	public boolean isWhite() {
		return team.isWhite();
	}

	@Override
	public boolean isEmpty() {
		return false;
	}
}
