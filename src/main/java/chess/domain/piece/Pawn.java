package chess.domain.piece;

import chess.domain.RelativePosition;
import chess.domain.Team;

public class Pawn extends Piece {

	private boolean hasMoved;

	public Pawn(final Team team) {
		super(team, Movement.PAWN);
		this.hasMoved = false;
	}

	@Override
	public boolean isMobile(RelativePosition relativePosition) {
		if (!hasMoved && relativePosition.isZeroAbsTwo()) {
			relativePosition = relativePosition.toUnit();
		}
		if (isMovementMobile(relativePosition)) {
			hasMoved = true;
			return true;
		}
		return false;
	}

	private boolean isMovementMobile(RelativePosition relativePosition) {
		if (team.isBlack()) {
			relativePosition = relativePosition.inverseByXAxis();
		}
		return movement.isMobile(relativePosition);
	}

	@Override
	public boolean isPawn() {
		return true;
	}

	@Override
	public PieceType getType() {
		return PieceType.PAWN;
	}
}
