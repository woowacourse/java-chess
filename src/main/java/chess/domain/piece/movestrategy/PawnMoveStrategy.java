package chess.domain.piece.movestrategy;

import java.util.Map;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.direction.Direction;
import chess.domain.piece.Piece;

public class PawnMoveStrategy implements MoveStrategy {

	@Override
	public boolean isMovable(Map<Coordinate, Piece> value, Coordinate from, Coordinate to) {
		Direction direction = Direction.of(from, to);

		if (direction.isVertical()) {
			return moveVertical(value, from, to, direction);
		}

		return moveDiagonal(value, from, to, direction);
	}

	private boolean moveVertical(Map<Coordinate, Piece> value, Coordinate from, Coordinate to, Direction direction) {
		Coordinate nextCoordinate = from.next(direction);
		Piece nextPiece = value.get(nextCoordinate);
		if (!nextPiece.isEmpty()) {
			return false;
		}

		if (nextCoordinate == to) {
			return true;
		}
		Piece fromPiece = value.get(from);
		if (from.isPawnStartRow(fromPiece.getTeam())) {
			nextCoordinate = nextCoordinate.next(direction);
			nextPiece = value.get(nextCoordinate);
			return nextPiece.isEmpty() && nextCoordinate == to;
		}
		return false;
	}

	private boolean moveDiagonal(Map<Coordinate, Piece> value, Coordinate from, Coordinate to, Direction direction) {
		Coordinate nextCoordinate = from.next(direction);
		Piece toPiece = value.get(to);
		return !toPiece.isEmpty() && nextCoordinate == to;
	}
}
