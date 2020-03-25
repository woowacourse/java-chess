package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

public class King extends Piece {
	private static final int KING_DISTANCE = 1;

	public King(Side side, Position position) {
		super(side, position);
	}

	@Override
	public boolean isInPath(Position targetPosition) {
		return this.position.isInDistance(KING_DISTANCE, targetPosition);
	}
}
