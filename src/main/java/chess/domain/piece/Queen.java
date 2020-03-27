package chess.domain.piece;

import java.util.List;

public class Queen extends Piece {
	public Queen(Position position, Color color) {
		super(position, color, Symbol.QUEEN);
	}

	@Override
	protected List<Direction> movableDirections(Piece piece, Direction direction) {
		return Direction.EVERY_DIRECTION;
	}

	@Override
	protected Direction findDirection(int x, int y) {
		return Direction.ofEvery(x, y);
	}
}
