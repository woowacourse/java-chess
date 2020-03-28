package chess.piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import chess.position.Position;

public class Knight extends Piece {
	public Knight(Team team) {
		super(team, "N");
	}

	@Override
	public List<Position> findTraceBetween(Position start, Position end) {
		if (start.isNotMultiplicationOfDifferenceBetweenFileAndRankIsTwo(end)) {
			throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
		}
		return new ArrayList<>(Collections.emptyList());
	}

	@Override
	protected String getInitialCharacter() {
		return this.initialCharacter;
	}
}
