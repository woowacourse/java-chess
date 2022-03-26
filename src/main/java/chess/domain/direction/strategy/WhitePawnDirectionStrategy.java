package chess.domain.direction.strategy;

import java.util.Optional;
import java.util.stream.Stream;

import chess.domain.Position;
import chess.domain.direction.BasicDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.Direction;

public class WhitePawnDirectionStrategy implements DirectionStrategy {

	@Override
	public Optional<? extends Direction> find(Position from, Position to) {
		return Stream.of(BasicDirection.NORTH, DiagonalDirection.NORTH_EAST, DiagonalDirection.NORTH_WEST)
			.filter(direction -> direction.confirm(from, to))
			.findAny();
	}
}
