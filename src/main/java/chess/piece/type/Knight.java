package chess.piece.type;

import java.util.Map;

import chess.location.Location;
import chess.score.Score;
import chess.team.Team;

public class Knight extends Piece {
	private static final char NAME = 'n';
	private static final double SCORE = 2.5;

	public Knight(Team team) {
		super(NAME, new Score(SCORE), team);
	}

	@Override
	public boolean canMove(Location now, Location after) {
		return now.isKnightRange(after);
	}

	@Override
	public boolean hasNotObstacle(Map<Location, Piece> board, Location now, Location destination) {
		return true;
	}
}
