package chess.domain.piece;

import chess.domain.RelativePosition;
import chess.domain.Team;

public class Bishop extends Piece {

	public Bishop(final Team team) {
		super(team, Movement.BISHOP, PieceType.BISHOP);
	}

	@Override
	public boolean isMobile(RelativePosition relativePosition) {
		return movement.isMobile(relativePosition);
	}
}
