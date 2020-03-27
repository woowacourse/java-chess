package chess.domain.piece;

import java.util.List;

import chess.domain.piece.exception.NotMovableException;

public class Pawn extends Piece {
	public Pawn(Position position, Color color) {
		super(position, color, Symbol.PAWN);
	}

	@Override
	protected List<Direction> movableDirections(Piece piece, Direction direction) {
		if (piece.isBlank() && direction != Direction.NORTH && direction != Direction.SOUTH) {
			throw new NotMovableException();
		}
		if (!piece.isBlank() && (direction == Direction.NORTH || direction == Direction.SOUTH)) {
			throw new NotMovableException();
		}
		return isBlack() ? Direction.BLACK_PAWN_DIRECTION : Direction.WHITE_PAWN_DIRECTION;
	}

	@Override
	protected Direction findDirection(int x, int y) {
		return Direction.of(x, y);
	}

	@Override
	public boolean isPawn() {
		return true;
	}
}
