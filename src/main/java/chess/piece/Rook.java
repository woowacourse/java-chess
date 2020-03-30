package chess.piece;

import chess.board.Location;
import chess.team.Team;

public class Rook extends Piece {
	private static final char name = 'r';
	private static final double score = 5;

	public Rook(Team team) {
		super(team);
	}

	@Override
	public boolean checkRange(Location now, Location after) {
		return now.isStraight(after);
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
