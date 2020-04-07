package chess.domain.piece;

import java.util.List;

import chess.domain.position.Position;

public class Queen extends Piece {
	private static final String INITIAL_CHARACTER = "Q";

	public Queen(Team team) {
		super(team, INITIAL_CHARACTER);
	}

	@Override
	public List<Position> findMoveModeTrace(Position from, Position to) {
		if (from.isNotStraght(to) && from.isNotDiagonal(to)) {
			throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
		}
		return Position.findMultipleStepTrace(from, to);
	}

	@Override
	protected String getInitialCharacter() {
		return INITIAL_CHARACTER;
	}

	@Override
	public double getScore() {
		return 9;
	}
}
