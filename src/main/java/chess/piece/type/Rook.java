package chess.piece.type;

import chess.board.Location;
import chess.team.Team;

public class Rook extends Piece {
	private static final char name = 'r';

	public Rook(Team team) {
		super(changeName(team));
	}

	private static char changeName(Team team) {
		if (team.isBlack()) {
			return Character.toUpperCase(name);
		}
		return name;
	}

	@Override
	public boolean canMove(Location now, Location after) {
		return now.isStraight(after);
	}
}
