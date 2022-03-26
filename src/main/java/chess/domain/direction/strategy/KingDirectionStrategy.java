package chess.domain.direction.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import chess.domain.position.Position;
import chess.domain.direction.BasicDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.Direction;

public class KingDirectionStrategy implements DirectionStrategy {

	@Override
	public Optional<? extends Direction> find(Position from, Position to) {
		List<Direction> directions = new ArrayList<>();
		directions.addAll(Arrays.asList(BasicDirection.values()));
		directions.addAll(Arrays.asList(DiagonalDirection.values()));
		return directions.stream()
			.filter(direction -> direction.confirm(from, to))
			.findAny();
	}
}
