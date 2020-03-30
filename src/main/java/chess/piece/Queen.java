package chess.piece;

import chess.board.Location;
import chess.team.Team;

public class Queen extends Piece {
	private static final char name = 'q';
	private static final double score = 9;

	public Queen(Team team) {
		super(team);
	}

	@Override
	public boolean checkRange(Location now, Location after) {
		return now.isQueenRange(after);
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
