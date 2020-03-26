package chess.domain.piece;

import java.util.List;

public class Blank extends Piece {
	public Blank(Position position) {
		super(position, Color.NONE);
	}

	@Override
	protected List<Direction> movableDirections(Piece piece, Direction direction) {
		throw new UnsupportedOperationException();
	}

	@Override
	protected Direction findDirection(int x, int y) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void move(Position position) {
		throw new UnsupportedOperationException();
	}
}
