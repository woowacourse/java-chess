package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

public class King extends Piece {
	private static final String NAME = "k";
	private static final int KING_DISTANCE = 1;
	private static final double SCORE = 0;

	public King(Side side, Position position) {
		super(side, position);
	}

	@Override
	public boolean isKing() {
		return true;
	}

	@Override
	public boolean isInPath(Position targetPosition) {
		return this.position.isInDistance(KING_DISTANCE, targetPosition);
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
