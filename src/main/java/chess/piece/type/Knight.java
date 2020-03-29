package chess.piece.type;

import java.util.Map;

import chess.board.Location;
import chess.score.Score;
import chess.team.Team;

public class Knight extends Piece {
	private static final char NAME = 'n';
	private static final double KNIGHT_SCORE = 2.5;

	public Knight(Team team) {
		super(changeName(team), new Score(KNIGHT_SCORE));
	}

	private static char changeName(Team team) {
		if (team.isBlack()) {
			return Character.toUpperCase(NAME);
		}
		return NAME;
	}

	@Override
	public boolean canMove(Location now, Location after) {
		return now.isKnightRange(after);
	}

	@Override
	public boolean hasObstacle(Map<Location, Piece> board, Location now, Location destination) {
		return false;
	}
}
