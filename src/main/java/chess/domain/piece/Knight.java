package chess.domain.piece;

import java.util.List;

public class Knight extends Piece {
	public Knight(Position position, Color color) {
		super(position, color, Symbol.KNIGHT);
	}

	@Override
	protected List<Direction> movableDirections(Piece piece) {
		return Direction.KNIGHT_DIRECTION;
	}

	@Override
	protected Direction findDirection(int x, int y) {
		return Direction.of(x, y);
	}
}
