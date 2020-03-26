package chess.piece;

import java.util.Collections;
import java.util.List;

import chess.position.Position;

public class King extends Piece {
	private static final String INITIAL_CHARACTER = "K";

	public King(Team team) {
		super(team);
	}

	@Override
	public List<Position> findReachablePositions(Position start, Position end) {
		if (start.isNotDistanceOneSquare(end)) {
			throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
		}
		return Collections.emptyList();
	}

	@Override
	protected String getInitialCharacter() {
		return INITIAL_CHARACTER;
	}
}
