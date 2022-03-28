package chess.domain.direction.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.direction.BasicDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.Direction;
import chess.domain.position.Position;

public class RoyalDirectionStrategy implements DirectionStrategy {

	private static final String INVALID_DIRECTION_ROYAL = "King이나 Queen이 갈 수 없는 방향입니다.";

	@Override
	public Direction find(Position from, Position to) {
		List<Direction> directions = new ArrayList<>();
		directions.addAll(Arrays.asList(BasicDirection.values()));
		directions.addAll(Arrays.asList(DiagonalDirection.values()));
		return directions.stream()
			.filter(direction -> direction.confirm(from, to))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(INVALID_DIRECTION_ROYAL));
	}
}
