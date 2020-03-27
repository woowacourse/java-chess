package chess.domain.piece;

import java.util.List;

public class King extends Piece {
	public King(Position position, Color color) {
		super(position, color, Symbol.KING);
	}

	@Override
	protected List<Direction> movableDirections(Piece piece, Direction direction) {
		return Direction.EVERY_DIRECTION;
	}

	@Override
	protected Direction findDirection(int x, int y) {
		return Direction.of(x, y);
	}

	@Override
	public boolean isKing() {
		return true;
	}
}
