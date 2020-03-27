package chess.domain.piece;

import java.util.List;

public class Bishop extends Piece {
	public Bishop(Position position, Color color) {
		super(position, color, Symbol.BISHOP);
	}

	@Override
	protected List<Direction> movableDirections(Piece piece, Direction direction) {
		return Direction.DIAGONAL_DIRECTION;
	}

	@Override
	protected Direction findDirection(int x, int y) {
		return Direction.ofDiagonal(x, y);
	}
}
