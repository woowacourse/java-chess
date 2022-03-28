package chess.domain.direction.strategy;

import java.util.Arrays;

import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.Direction;
import chess.domain.position.Position;

public class BishopDirectionStrategy implements DirectionStrategy {

	private static final String INVALID_DIRECTION_BISHOP = "비숍이 갈 수 없는 방향입니다.";

	@Override
	public Direction find(Position from, Position to) {
		return Arrays.stream(DiagonalDirection.values())
			.filter(direction -> direction.confirm(from, to))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(INVALID_DIRECTION_BISHOP));
	}
}
