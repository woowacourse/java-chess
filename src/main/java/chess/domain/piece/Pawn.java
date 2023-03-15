package chess.domain.piece;

import chess.domain.Movement;
import chess.domain.RelativePosition;
import chess.domain.Team;

public class Pawn implements Piece {

	private final Movement movement;
	private final Team team;
	private boolean hasMoved;

	public Pawn(final Team team) {
		this.team = team;
		this.movement = Movement.PAWN;
		this.hasMoved = false;
	}

	@Override
	public boolean isMobile(RelativePosition relativePosition) {
		if (!hasMoved && relativePosition.isZeroAbsTwo()) {
			relativePosition = relativePosition.toUnit();
		}
		boolean isMobile = isMovementMobile(relativePosition);
		if(isMobile){
			hasMoved = true;
		}
		return isMobile;
	}

	private boolean isMovementMobile(RelativePosition relativePosition) {
		if (team.isBlack()) {
			relativePosition = relativePosition.inverseByXAxis();
		}
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
}
