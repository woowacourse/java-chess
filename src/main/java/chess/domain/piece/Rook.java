package chess.domain.piece;

import java.util.List;

public class Rook extends Piece {
	public Rook(Position position, Color color) {
		super(position, color, Symbol.ROOK);
	}

	@Override
	protected List<Direction> movableDirections(Piece piece) {
		return Direction.LINEAR_DIRECTION;
	}

	@Override
	protected Direction findDirection(int x, int y) {
		return Direction.ofLinear(x, y);
	}
}
