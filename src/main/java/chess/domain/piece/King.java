package chess.domain.piece;

import java.util.List;

public class King extends Piece {
	public King(Position position, Color color) {
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
