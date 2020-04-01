package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

public class Pawn extends Piece {
	private static final int INIT_PAWN_DISTANCE = 2;
	private static final int PAWN_DISTANCE = 1;
	private static final String NAME = "p";
	private static final double SCORE = 0.5;

	public Pawn(Side side, Position position) {
		super(side, position);
		this.name = NAME;
		this.score = SCORE;
	}

	@Override
	public boolean isInPath(Position targetPosition) {
		if (isFirstState()) {
			return isPawnPath(targetPosition, INIT_PAWN_DISTANCE);
		}
		return isPawnPath(targetPosition, PAWN_DISTANCE);
	}

	@Override
	public boolean canAttack(Piece piece) {
		return position.isInDiagonal(piece.position) &&
				position.isInDistance(PAWN_DISTANCE, piece.position) &&
				isForwardAttack(piece.position) && !isSameSide(piece);
	}

	private boolean isFirstState() {
		return side.isInitPawnRow(position);
	}

	private boolean isPawnPath(Position target, int distance) {
		return position.isSameCol(target) && position.isInDistance(distance, target) && isForwardAttack(target);
	}
}
