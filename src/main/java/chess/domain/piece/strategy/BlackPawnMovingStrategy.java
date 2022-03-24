package chess.domain.piece.strategy;

import java.util.Optional;

import chess.domain.Position;
import chess.domain.direction.BasicDirection;
import chess.domain.direction.Direction;
import chess.domain.direction.DirectionGenerator;

public class BlackPawnMovingStrategy implements MovingStrategy {

	private static final int BLACK_PAWN_INITIAL_ROW = 7;

	@Override
	public boolean check(Position from, Position to) {
		Optional<? extends Direction> nullableDirection = DirectionGenerator.generateOfBlackPawn(from, to);
		if (nullableDirection.isEmpty()) {
			return false;
		}

		Direction direction = nullableDirection.get();

		if (from.isSameRow(BLACK_PAWN_INITIAL_ROW) && direction == BasicDirection.SOUTH) {
			return from.canReach(to, direction.getUnitPosition(), 2);
		}
		return from.canReach(to, direction.getUnitPosition(), 1);
	}
}
