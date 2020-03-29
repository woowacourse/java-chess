package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

public class Bishop extends Piece {
	public static final int SCORE = 3;

	public Bishop(Side side, Position position) {
		super(side, position);
	}

	@Override
	public double getScore() {
		return SCORE;
	}

	@Override
	public boolean isInPath(Position targetPosition) {
		return position.isInDiagonal(targetPosition);
	}
}
