package chess.domain.piece;

import chess.domain.Team;

public class King extends Piece {

	public King(final Team team) {
		super(team, Movement.KING);
	}

	@Override
	public PieceType getType() {
		return PieceType.KING;
	}
}
