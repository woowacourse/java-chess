package chess.domain.piece;

import chess.domain.RelativePosition;
import chess.domain.Team;

public class InitialPawn extends Piece {

	public InitialPawn(final Team team) {
		super(team, Movement.INITIAL_PAWN);
	}

	@Override
	public boolean isMobile(RelativePosition relativePosition) {
		return relativePosition.isXZeroAndYOneOrTwo() && isMovementMobile(relativePosition.getGcdDivided());
	}

	private boolean isMovementMobile(RelativePosition relativePosition) {
		if (team.isBlack()) {
			relativePosition = relativePosition.inverseByXAxis();
		}
		return movement.isMobile(relativePosition);
	}

	@Override
	public boolean isInitialPawn() {
		return true;
	}

	@Override
	public PieceType getType() {
		return PieceType.INITIAL_PAWN;
	}
}
