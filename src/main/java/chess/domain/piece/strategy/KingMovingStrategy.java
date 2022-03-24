package chess.domain.piece.strategy;

import java.util.Optional;

import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.direction.DirectionGenerator;

public class KingMovingStrategy implements MovingStrategy {

	@Override
	public boolean check(Position from, Position to) {
		Optional<? extends Direction> nullableDirection = DirectionGenerator.generateOfRoyal(from, to);
		if (nullableDirection.isEmpty()) {
			return false;
		}
		Direction direction = nullableDirection.get();
		return from.canReach(to, direction.getUnitPosition(), 1);
	}
}
