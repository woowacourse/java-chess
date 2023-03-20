package chess.domain.piece;

import chess.domain.Team;

public class Bishop extends Piece {

	public Bishop(final Team team) {
		super(team, Movement.BISHOP);
	}

	@Override
	public PieceType getType() {
		return PieceType.BISHOP;
	}
}
