package chess.domain.MoveRule;

import chess.domain.Position;

public class Bishop extends AbstractRule {
	private static Bishop INSTANCE = new Bishop();
	private static final double SCORE = 3;
	public static final String NAME = "BISHOP";

	private Bishop() {
		super(SCORE, NAME);
	}

	public static Bishop getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean isValidMove(final Position origin, final Position target) {
		return origin.isDiagonal(target);
	}
}
