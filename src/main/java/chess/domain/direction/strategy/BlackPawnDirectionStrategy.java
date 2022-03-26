package chess.domain.direction.strategy;

import java.util.Optional;
import java.util.stream.Stream;

import chess.domain.Position;
import chess.domain.direction.BasicDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.Direction;

public class BlackPawnDirectionStrategy implements DirectionStrategy {

	@Override
	public Optional<? extends Direction> find(Position from, Position to) {
		return Stream.of(BasicDirection.SOUTH, DiagonalDirection.SOUTH_EAST, DiagonalDirection.SOUTH_WEST)
			.filter(direction -> direction.confirm(from, to))
			.findAny();
	}
}
