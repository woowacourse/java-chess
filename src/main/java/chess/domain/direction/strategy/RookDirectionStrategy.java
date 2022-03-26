package chess.domain.direction.strategy;

import java.util.Arrays;
import java.util.Optional;

import chess.domain.Position;
import chess.domain.direction.BasicDirection;
import chess.domain.direction.Direction;

public class RookDirectionStrategy implements DirectionStrategy {

	@Override
	public Optional<? extends Direction> find(Position from, Position to) {
		return Arrays.stream(BasicDirection.values())
			.filter(direction -> direction.confirm(from, to))
			.findAny();
	}
}
