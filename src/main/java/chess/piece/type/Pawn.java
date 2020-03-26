package chess.piece.type;

import chess.board.Location;
import chess.team.Team;

public class Pawn extends Piece {
	private static final char name = 'p';

	public Pawn(Team team) {
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
		int value = 1;
		if (isBlack()) {
			value = -1;
		}
		// now가 초기위치인지 검사
		if (now.isInitialPawnLocation(isBlack())) {
			return now.isInitialPawnForwardRange(after, value) || now.isForwardDiagonal(after, value);
		}
		return now.isForwardDiagonal(after, value) || now.isPawnForwardRange(after, value);
	}
}
