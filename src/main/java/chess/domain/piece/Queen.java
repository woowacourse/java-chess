package chess.domain.piece;

import java.util.List;

public class Queen extends Piece {
	public Queen(Position position, Color color) {
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
