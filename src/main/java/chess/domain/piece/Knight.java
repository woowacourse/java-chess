package chess.domain.piece;

import chess.domain.RelativePosition;
import chess.domain.Team;

public class Knight extends Piece {

	public Knight(final Team team){
		super(team, Movement.KNIGHT, PieceType.KNIGHT);
	}

	@Override
	public boolean isMobile(RelativePosition relativePosition) {
		return movement.isMobile(relativePosition);
	}
}
