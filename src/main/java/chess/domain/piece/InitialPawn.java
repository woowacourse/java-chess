package chess.domain.piece;

import chess.domain.RelativePosition;
import chess.domain.Team;

public class InitialPawn extends Piece {

	public InitialPawn(final Team team) {
		super(team, Movement.INITIAL_PAWN, PieceType.INITIAL_PAWN);
	}

	@Override
	public boolean isMobile(RelativePosition relativePosition) {
		return relativePosition.isXZeroAndYOneOrTwo() && isMovementMobile(relativePosition.getGcdDivided());
	}

	private boolean isMovementMobile(RelativePosition relativePosition) {
		if (team == Team.BLACK) {
			relativePosition = relativePosition.inverseByXAxis();
		}
		return movement.isMobile(relativePosition);
	}
}
