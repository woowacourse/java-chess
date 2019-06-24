package chess.domain.moverule;

import chess.domain.Position;

public class Rook extends AbstractMoveRule {
	private static Rook INSTANCE = new Rook();
	private static final double SCORE = 5;
	public static final String NAME = "ROOK";

	private Rook() {
		super(SCORE, NAME);
	}

	public static Rook getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean isValidMove(final Position origin, final Position target) {
		return origin.isLevel(target) || origin.isPerpendicular(target);
	}
}