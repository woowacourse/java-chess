package chess.domain.piece.movestrategy;

import java.util.Map;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.direction.Direction;
import chess.domain.piece.Piece;

public class RepeatableMoveStrategy implements MoveStrategy {

	@Override
	public boolean isMovable(Map<Coordinate, Piece> value, Coordinate from, Coordinate to) {
		Direction direction = Direction.of(from, to);

		return isNotObstacleExist(value, direction, from, to);
	}

	private boolean isNotObstacleExist(Map<Coordinate, Piece> value,
		Direction direction,
		Coordinate from,
		Coordinate to) {
		Coordinate nextCoordinate = from.next(direction);
		if (nextCoordinate == to) {
			return true;
		}

		Piece piece = value.get(nextCoordinate);
		if (!piece.isEmpty()) {
			return false;
		}

		return isNotObstacleExist(value, direction, nextCoordinate, to);
	}

}
