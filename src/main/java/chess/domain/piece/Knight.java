package chess.domain.piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.domain.position.Position;

public class Knight extends Piece {
	private static final String INITIAL_CHARACTER = "N";

	public Knight(Team team) {
		super(team, INITIAL_CHARACTER);
	}

	@Override
	public List<Position> findMoveModeTrace(Position from, Position to) {
		if (from.isNotKnightMovable(to)) {
			throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
		}
		return new ArrayList<>(Collections.emptyList());
	}

	@Override
	protected String getInitialCharacter() {
		return INITIAL_CHARACTER;
	}

	@Override
	public double getScore() {
		return 2.5;
	}
}
