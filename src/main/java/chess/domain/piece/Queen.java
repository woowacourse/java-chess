package chess.domain.piece;

import chess.domain.Team;

public class Queen extends Piece {

	public Queen(final Team team) {
		super(team, Movement.QUEEN, PieceType.QUEEN);
	}
}
