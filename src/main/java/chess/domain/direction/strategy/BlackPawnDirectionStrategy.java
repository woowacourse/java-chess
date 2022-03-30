package chess.domain.direction.strategy;

import java.util.stream.Stream;

import chess.domain.direction.BasicDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.Direction;
import chess.domain.position.Position;

public class BlackPawnDirectionStrategy implements DirectionStrategy {

	private static final String INVALID_DIRECTION_PAWN = "Black Pawn이 갈 수 없는 방향입니다.";

	@Override
	public Direction find(Position from, Position to) {
		return Stream.of(BasicDirection.SOUTH, DiagonalDirection.SOUTH_EAST, DiagonalDirection.SOUTH_WEST)
			.filter(direction -> direction.confirm(from, to))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(INVALID_DIRECTION_PAWN));
	}
}
