package chess.domain.direction.strategy;

import java.util.Arrays;
import java.util.Optional;

import chess.domain.position.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.KnightDirection;

public class KnightDirectionStrategy implements DirectionStrategy {

	@Override
	public Optional<? extends Direction> find(Position from, Position to) {
		return Arrays.stream(KnightDirection.values())
               			.filter(direction -> direction.confirm(from, to))
               			.findAny();
	}
}
