package chess.domain.piece;

import java.util.List;

import chess.domain.piece.exception.NotMovableException;

public class Pawn extends Piece {
	public Pawn(Position position, Color color) {
		super(position, color);
	}

	@Override
	protected List<Direction> movableDirections(Piece piece, Direction direction) {
		if (piece instanceof Blank && direction != Direction.NORTH && direction != Direction.SOUTH) {
			throw new NotMovableException();
		}
		if (!(piece instanceof Blank) && (direction == Direction.NORTH || direction == Direction.SOUTH)) {
			throw new NotMovableException();
		}
		return isBlack() ? Direction.blackPawnDirection() : Direction.whitePawnDirection();
	}

	@Override
	protected Direction findDirection(int x, int y) {
		return Direction.of(x, y);
	}
}
