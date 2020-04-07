package chess.domain.piece;

import java.util.Collections;
import java.util.List;

import chess.domain.position.Position;

public class King extends Piece {
	private static final String INITIAL_CHARACTER = "K";

	public King(Team team) {
		super(team, INITIAL_CHARACTER);
	}

	@Override
	public List<Position> findMoveModeTrace(Position from, Position to) {
		if (from.isNotDistanceOneSquare(to)) {
			throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
		}
		return Collections.emptyList();
	}

	@Override
	protected String getInitialCharacter() {
		return INITIAL_CHARACTER;
	}

	@Override
	public boolean isKing() {
		return true;
	}

	@Override
	public double getScore() {
		return 0;
	}
}
