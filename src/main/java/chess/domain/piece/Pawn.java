package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

public class Pawn extends Piece {
	public static final double SCORE = 0.5;
	private static final String NAME = "p";
	private static final int INIT_PAWN_DISTANCE = 2;
	private static final int PAWN_DISTANCE = 1;

	public Pawn(Side side, Position position) {
		super(side, position);
	}

	@Override
	public boolean canAttack(Piece piece) {
		return position.isInDiagonal(piece.position) &&
				position.isInDistance(PAWN_DISTANCE, piece.position) &&
				isAttackForward(piece.position) && !isSameSide(piece);
	}

	@Override
	public boolean canNotAttack(Piece piece) {
		return !this.canAttack(piece);
	}

	@Override
	public boolean isInPath(Position targetPosition) {
		int pawnDistance = PAWN_DISTANCE;
		if (isFirstState()) {
			pawnDistance = INIT_PAWN_DISTANCE;
		}
		return isPawnPath(targetPosition, pawnDistance);
	}

	private boolean isFirstState() {
		return side.isInitPawnRow(position);
	}

	private boolean isPawnPath(Position target, int distance) {
		return position.isSameCol(target) && position.isInDistance(distance,
				target) && isAttackForward(target);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public double getScore() {
		return SCORE;
	}
}
