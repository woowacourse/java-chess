package chess.piece;

import java.util.Map;

import chess.board.Location;
import chess.team.Team;

public class King extends Piece {
	private static final char name = 'k';
	private static final double score = 0;

	public King(Team team) {
		super(team);
	}

	@Override
	public boolean checkRange(Location now, Location after) {
		return now.isKingRange(after);
	}

	@Override
	public boolean checkObstacle(Map<Location, Piece> board, Location now, Location destination) {
		return false;
	}

	@Override
	public double getScore() {
		return score;
	}

	@Override
	protected char getName() {
		return name;
	}
}
