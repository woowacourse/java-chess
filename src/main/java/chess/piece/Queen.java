package chess.piece;

import chess.board.Location;
import chess.team.Team;

public class Queen extends Piece {
	private static final char name = 'q';
	private static final double score = 9;

	public Queen(Team team) {
		super(changeName(team));
	}

	private static char changeName(Team team) {
		if (team.isBlack()) {
			return Character.toUpperCase(name);
		}
		return name;
	}

	@Override
	public boolean checkRange(Location now, Location after) {
		return now.isQueenRange(after);
	}

	@Override
	public double getScore() {
		return score;
	}
}
