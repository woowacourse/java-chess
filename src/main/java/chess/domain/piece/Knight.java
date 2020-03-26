package chess.domain.piece;

import java.util.List;

public class Knight extends Piece {
	public Knight(Position position, Color color) {
		super(position, color);
	}

	@Override
	protected List<Direction> movableDirections(Piece piece, Direction direction) {
		return null;
	}

	@Override
	protected Direction findDirection(int x, int y) {
		return null;
	}
}
