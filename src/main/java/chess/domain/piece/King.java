package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.Position;

public class King extends Piece {
	public static final String WHITE_KING = "\u2654";
	public static final String BLACK_KING = "\u265a";

	public King(Team team, Position position) {
		super(team, position);
	}

	public static King of(Team team, Position position) {
		return new King(team, position);
	}

	@Override
	public String toString() {
		if (team.equals(Team.WHITE)) {
			return WHITE_KING;
		}
		return BLACK_KING;
	}
}