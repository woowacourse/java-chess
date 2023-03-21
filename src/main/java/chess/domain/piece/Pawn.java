package chess.domain.piece;

import chess.domain.RelativePosition;
import chess.domain.Team;

public class Pawn extends Piece {

	public Pawn(final Team team) {
		super(team, Movement.PAWN, PieceType.PAWN);
	}

	@Override
	public boolean isMobile(RelativePosition relativePosition) {
		if (team.isBlack()) {
			relativePosition = relativePosition.inverseByXAxis();
		}
		return movement.isMobile(relativePosition);
	}
}
