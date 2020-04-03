package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

public class Queen extends Piece {
	private static final String NAME = "q";
	private static final double SCORE = 9;

	public Queen(Side side, Position position) {
		super(side, position);
		this.name = NAME;
		this.score = SCORE;
	}

	@Override
	public boolean isInPath(Position targetPosition) {
		return position.isLinear(targetPosition);
	}
}
