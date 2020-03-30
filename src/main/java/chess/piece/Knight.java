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
	public boolean isInvalidMovementWithoutConsideringOtherPieces(Position source, Position target) {
		return source.isNotMultiplicationOfDifferenceBetweenFileAndRankIsTwo(target);
	}

	@Override
	public List<Position> movePathExceptSourceAndTarget(Position start, Position end) {
		return new ArrayList<>(Collections.emptyList());
	}
}
