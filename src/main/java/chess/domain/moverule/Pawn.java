package chess.domain.moverule;

import chess.domain.MoveRule;
import chess.domain.Position;

public enum Pawn implements MoveRule {
	FIRST_TOP(-2),
	SECOND_TOP(-1),
	FIRST_BOTTOM(2),
	SECOND_BOTTOM(1);

	public static final String NAME = "PAWN";
	private static final int ZERO_VECTOR = 0;
	private static final double SCORE = 1;
	private static final int STANDARD_OF_SIGN = 0;
	private static final int ATTACK_MOVEMENT_DISTANCE = 2;

	private final int vector;

	Pawn(final int vector) {
		this.vector = vector;
	}

	@Override
	public boolean isValidMove(final Position origin, final Position target) {
		final int vector = origin.vectorOfRow(target);
		return origin.isPerpendicular(target) && (isBottomMovable(vector) || isTopMovable(vector));
	}

	private boolean isTopMovable(final int vector) {
		return this.vector <= vector && vector < ZERO_VECTOR;
	}

	private boolean isBottomMovable(final int vector) {
		return this.vector >= vector && vector > ZERO_VECTOR;
	}

	@Override
	public boolean isValidAttack(final Position origin, final Position target) {
		if (!origin.isDiagonal(target)) {
			return false;
		}
		final int vector = origin.vectorOfRow(target);
		return origin.isSameSumOfPosition(target, ATTACK_MOVEMENT_DISTANCE) && isSameSign(vector);
	}

	private boolean isSameSign(final int vector) {
		return Integer.compare(STANDARD_OF_SIGN, this.vector) == Integer.compare(STANDARD_OF_SIGN, vector);
	}

	@Override
	public boolean isSameName(final String name) {
		return NAME.equals(name);
	}

	@Override
	public double getScore() {
		return SCORE;
	}

	@Override
	public String getName() {
		return NAME;
	}
}
