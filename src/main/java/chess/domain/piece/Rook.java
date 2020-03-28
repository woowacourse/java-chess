package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;

public class Rook extends Piece {
	public static final String WHITE_ROOK = "\u2656";
	public static final String BLACK_ROOK = "\u265c";

	public Rook(Team team, Position position) {
		super(team, position);
	}

	public static Rook of(Team team, Position position) {
		return new Rook(team, position);
	}

	@Override
	public String toString() {
		if (team.equals(Team.WHITE)) {
			return WHITE_ROOK;
		}
		return BLACK_ROOK;
	}
}

