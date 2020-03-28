package chess.piece;

import java.util.Map;

import chess.board.Location;
import chess.team.Team;

public class King extends Piece {
	private static final char name = 'k';
	private static final double score = 0;

	public King(Team team) {
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
		return now.isKingRange(after);
	}

	@Override
	public double getScore(boolean hasVerticalEnemy) {
		return score;
	}

	@Override
	public boolean hasObstacle(Map<Location, Piece> board, Location now, Location destination) {
		return false;
	}
}
