package chess.domain.piece;

import chess.domain.RelativePosition;
import chess.domain.Team;

public class King extends Piece {

	public King(final Team team) {
		super(team, Movement.KING, PieceType.KING);
	}

	@Override
	public boolean isMobile(RelativePosition relativePosition) {
		return movement.isMobile(relativePosition);
	}
}
