package chess.piece;

import java.util.Map;

import chess.board.Location;
import chess.team.Team;

public class Knight extends Piece {
	private static final char name = 'n';
	private static final double score = 2.5;

	public Knight(Team team) {
		super(team);
	}

	@Override
	public boolean checkRange(Location now, Location after) {
		return now.isKnightRange(after);
	}

	@Override
	public double getScore() {
		return score;
	}

	@Override
	protected char getName() {
		return name;
	}

	@Override
	public boolean checkObstacle(Map<Location, Piece> board, Location now, Location destination) {
		return false;
	}
}
