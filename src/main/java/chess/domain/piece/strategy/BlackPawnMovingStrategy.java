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

		int distance = from.calculateDistance(to);
		Direction direction = nullableDirection.get();
		if (isFirstStartWithTwoStep(from, direction, distance)) {
			return true;
		}
		if (isDiagonalStep(direction, distance)) {
			return true;
		}
		return distance == 1;
	}

	private boolean isDiagonalStep(Direction direction, int distance) {
		return distance == 2 && direction != BasicDirection.SOUTH;
	}

	private boolean isFirstStartWithTwoStep(Position from, Direction direction, int distance) {
		return distance == 2
			&& direction == BasicDirection.SOUTH
			&& from.isSameRow(BLACK_PAWN_INITIAL_ROW);
	}
}
