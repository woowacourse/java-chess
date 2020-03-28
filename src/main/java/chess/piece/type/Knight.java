package chess.piece.type;

import java.util.Map;

import chess.board.Location;
import chess.team.Team;

public class Knight extends Piece {
	private static final char name = 'n';
	private static final double score = 2.5;

	public Knight(Team team) {
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
		return now.isKnightRange(after);
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
