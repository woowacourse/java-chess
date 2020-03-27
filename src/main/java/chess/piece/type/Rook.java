package chess.piece.type;

import chess.board.Location;
import chess.score.Score;
import chess.team.Team;

public class Rook extends Piece {
	public static final char NAME = 'r';
	private static final int ROOK_SCORE = 5;

	public Rook(Team team) {
		super(changeName(team), new Score(ROOK_SCORE));
	}

	private static char changeName(Team team) {
		if (team.isBlack()) {
			return Character.toUpperCase(NAME);
		}
		return NAME;
	}

	@Override
	public boolean canMove(Location now, Location after) {
		return now.isStraight(after);
	}
}
