package chess.piece;

import chess.board.Location;
import chess.team.Team;

public class Bishop extends Piece {
	private static final char name = 'b';
	private static final double score = 3;

	public Bishop(Team team) {
		super(team);
	}

	@Override
	public boolean checkRange(Location now, Location after) {
		return now.isDiagonal(after);
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

