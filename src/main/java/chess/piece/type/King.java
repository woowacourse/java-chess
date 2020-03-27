package chess.piece.type;

import java.util.Map;

import chess.board.Location;
import chess.score.Score;
import chess.team.Team;

public class King extends Piece {
	public static final char NAME = 'k';
	private static final int KING_SCORE = 0;

	public King(Team team) {
		super(changeName(team), new Score(KING_SCORE));
	}

	private static char changeName(Team team) {
		if (team.isBlack()) {
			return Character.toUpperCase(NAME);
		}
		return NAME;
	}

	@Override
	public boolean canMove(Location now, Location after) {
		return now.isKingRange(after);
	}

	@Override
	public boolean hasObstacle(Map<Location, Piece> board, Location now, Location destination) {
		return false;
	}
}
