package chess.domain.piece;

import chess.domain.Team;

public class Rook extends Piece {

	public Rook(final Team team) {
		super(team, Movement.ROOK);
	}

	@Override
	public PieceType getType() {
		return PieceType.ROOK;
	}
}
