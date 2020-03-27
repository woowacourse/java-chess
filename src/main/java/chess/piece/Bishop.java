package chess.piece;

import java.util.List;

import chess.position.Position;

public class Bishop extends Piece {
	private static final String INITIAL_CHARACTER = "B";

	public Bishop(Team team) {
		super(team);
	}

	@Override
	public List<Position> findTraceBetween(Position start, Position end) {
		if (start.isNotDiagonal(end)) {
			throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
		}
		return Position.findDiagonalTrace(start, end);
	}

	@Override
	protected String getInitialCharacter() {
		return INITIAL_CHARACTER;
	}
}
