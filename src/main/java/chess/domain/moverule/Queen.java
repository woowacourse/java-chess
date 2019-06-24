package chess.domain.moverule;

import chess.domain.Position;

public class Queen extends AbstractMoveRule {
	private static Queen INSTANCE = new Queen();
	private static final double SCORE = 9;
	public static final String NAME = "QUEEN";

	private Queen() {
		super(SCORE, NAME);
	}

	public static Queen getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean isValidMove(final Position origin, final Position target) {
		return origin.isDiagonal(target) || origin.isPerpendicular(target) || origin.isLevel(target);
	}
}
