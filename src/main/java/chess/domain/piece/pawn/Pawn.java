package chess.domain.piece.pawn;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public abstract class Pawn extends Piece {

	public static final String WHITE_PAWN = "\u2659";
	public static final String BLACK_PAWN = "\u265f";

	public Pawn(Team team, Position position) {
		super(team, position);
	}

	@Override
	public String toString() {
		if (team.equals(Team.WHITE)) {
			return WHITE_PAWN;
		}
		return BLACK_PAWN;
	}
}