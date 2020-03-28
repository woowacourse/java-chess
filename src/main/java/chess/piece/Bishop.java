package chess.piece;

import chess.board.Location;
import chess.team.Team;

public class Bishop extends Piece {
	private static final char name = 'b';
	private static final double score = 3;

	public Bishop(Team team) {
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
		return now.isDiagonal(after);
	}

	@Override
	public double getScore(boolean hasVerticalEnemy) {
		return score;
	}
}

