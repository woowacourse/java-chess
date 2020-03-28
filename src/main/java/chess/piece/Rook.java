package chess.piece;

import chess.board.Location;
import chess.team.Team;

public class Rook extends Piece {
	private static final char name = 'r';
	private static final double score = 5;

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

	@Override
	public double getScore(boolean hasVerticalEnemy) {
		return score;
	}
}
