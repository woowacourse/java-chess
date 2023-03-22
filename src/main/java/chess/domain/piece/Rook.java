package chess.domain.piece;

import chess.domain.RelativePosition;
import chess.domain.Team;

public class Rook extends Piece {

	public Rook(final Team team) {
		super(team, Movement.ROOK, PieceType.ROOK);
	}

	@Override
	public boolean isMobile(RelativePosition relativePosition) {
		return movement.isMobile(relativePosition);
	}
}
