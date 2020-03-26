package chess.domain.piece;

import chess.domain.Side;
import chess.domain.position.Position;

public class Rook extends Piece {
	private static final String NAME = "r";
	public Rook(Side side, Position position) {
		super(side, position);
		this.name = NAME;
	}

	@Override
	public boolean isInPath(Position targetPosition) {
		return position.isSameCol(targetPosition) || position.isSameRow(targetPosition);
	}
}
