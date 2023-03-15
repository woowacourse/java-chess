package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.RelativePosition;
import chess.domain.Team;

public class King implements Piece {

	private final Team team;
	private final Movement movement;

	public King(final Team team) {
		this.team = team;
		this.movement = Movement.KING;
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
	public PieceType getType() {
		return PieceType.KING;
	}
}
