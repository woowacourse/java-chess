package chess.domain.piece.movestrategy;

import java.util.Map;

import chess.domain.board.coordinate.Coordinate;
import chess.domain.direction.Direction;
import chess.domain.piece.Piece;

public class NonRepeatableMoveStrategy implements MoveStrategy {

	@Override
	public boolean isMovable(Map<Coordinate, Piece> value, Coordinate from, Coordinate to) {
		Direction direction = Direction.of(from, to);

		return from.next(direction) == to;
	}
}
