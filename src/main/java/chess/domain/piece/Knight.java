package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

public class Knight extends Piece {
	private static final int KNIGHT_DISTANCE = 2;
	private static final String NAME = "n";
	private static final double SCORE = 2.5;

	public Knight(Side side, Position position) {
		super(side, position);
		this.name = NAME;
		this.score = SCORE;
	}

	@Override
	public boolean isInPath(Position targetPosition) {
		return !position.isLinear(targetPosition) && position.isInDistance(KNIGHT_DISTANCE, targetPosition);
	}
}
