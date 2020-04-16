package chess.domain.chessPiece.pieceType;

import static chess.domain.position.MoveDirection.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.position.MoveDirection;

public enum PieceDirection {

	ALL(Arrays.asList(MoveDirection.values())),
	DIAGONAL(Arrays.asList(NE, SE, SW, NW)),
	AXIS(Arrays.asList(N, E, S, W)),
	BLACK_PAWN(Arrays.asList(S), Arrays.asList(SW, SE)),
	WHITE_PAWN(Arrays.asList(N), Arrays.asList(NW, NE));

	private final List<MoveDirection> movableDirections;
	private final List<MoveDirection> catchableDirections;

	PieceDirection(final List<MoveDirection> movableDirections, final List<MoveDirection> catchableDirections) {
		this.movableDirections = movableDirections;
		this.catchableDirections = catchableDirections;
	}

	PieceDirection(final List<MoveDirection> movableDirections) {
		this(movableDirections, movableDirections);
	}

	public List<MoveDirection> getMovableDirections() {
		return movableDirections;
	}

	public List<MoveDirection> getCatchableDirections() {
		return catchableDirections;
	}

}
