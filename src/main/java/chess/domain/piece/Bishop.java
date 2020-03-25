package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

public class Bishop extends Piece {
	public Bishop(Side side, Position position) {
		super(side, position);
	}

	@Override
	public boolean isInPath(Position targetPosition) {
		return position.isInDiagonal(targetPosition);
	}
}
