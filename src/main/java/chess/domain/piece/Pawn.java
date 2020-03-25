package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

public class Pawn extends Piece {
	private static final int INIT_PAWN_DISTANCE = 2;
	private static final int PAWN_DISTANCE = 1;

	public Pawn(Side side, Position position) {
		super(side, position);
	}

	@Override
	public boolean isInPath(Position targetPosition) {
		if (isFirstState()) {
			return isPawnPath(targetPosition, INIT_PAWN_DISTANCE);
		}
		return isPawnPath(targetPosition, PAWN_DISTANCE);
	}

	private boolean isFirstState() {
		return side.isInitPawnRow(position);
	}

	private boolean isPawnPath(Position targetPosition, int distance) {
		return position.isSameCol(targetPosition) && position.isInDistance(distance, targetPosition);
	}
}
