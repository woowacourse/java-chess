package chess.domain.MoveRule;

import chess.domain.Position;

public class Knight extends AbstractRule {
	private static Knight INSTANCE = new Knight();
	private static final double SCORE = 2.5;
	public static final String NAME = "KNIGHT";

	private Knight() {
		super(SCORE, NAME);
	}

	public static Knight getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean isValidMove(final Position origin, final Position target) {
		return origin.isSameSumOfPosition(target, 3) && origin.isSameSubOfPosition(target, 1);
	}
}
