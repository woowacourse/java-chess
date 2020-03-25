package chess.piece;

import java.util.List;

import chess.position.Position;

public class Knight extends Piece {
	private static final String INITIAL_CHARACTER = "K";

	public Knight(Team team) {
		super(team);
	}

	@Override
	public List<Position> findReachablePositions(Position start, Position end) {
		return null;
	}

	@Override
	protected String getInitialCharacter() {
		return INITIAL_CHARACTER;
	}
}
