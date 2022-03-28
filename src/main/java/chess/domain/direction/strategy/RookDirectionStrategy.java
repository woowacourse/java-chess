package chess.domain.direction.strategy;

import java.util.Arrays;
import java.util.Optional;

import chess.domain.position.Position;
import chess.domain.direction.BasicDirection;
import chess.domain.direction.Direction;

public class RookDirectionStrategy implements DirectionStrategy {

	private static final String INVALID_DIRECTION_ROOK = "Rook이 갈 수 없는 방향입니다.";

	@Override
	public Direction find(Position from, Position to) {
		return Arrays.stream(BasicDirection.values())
			.filter(direction -> direction.confirm(from, to))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(INVALID_DIRECTION_ROOK));
	}
}
