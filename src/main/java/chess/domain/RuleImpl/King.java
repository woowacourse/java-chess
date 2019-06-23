package chess.domain.RuleImpl;

import chess.domain.Position;

public class King extends AbstractRule {
	private static King INSTANCE = new King();
	private static final double SCORE = 0;
	public static final String NAME = "KING";

	private King() {
		super(SCORE, NAME);
	}

	public static King getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean isValidMove(final Position origin, final Position target) {
		return isLengthOne(origin, target) || isLengthTwo(origin, target);
	}

	private boolean isLengthOne(final Position origin, final Position target) {
		return origin.isMoveAnyWhereSum(target, 1);
	}

	private boolean isLengthTwo(final Position origin, final Position target) {
		return origin.isMoveAnyWhereSum(target, 2) && origin.isMoveAnyWhereSub(target, 0);
	}
}
