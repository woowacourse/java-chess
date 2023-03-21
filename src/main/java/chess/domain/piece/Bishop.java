package chess.domain.piece;

import chess.domain.Team;

public class Bishop extends Piece {

	public Bishop(final Team team) {
		super(team, Movement.BISHOP, PieceType.BISHOP);
	}
}
