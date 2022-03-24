package chess.domain.piece.strategy;

import java.util.Optional;

import chess.domain.Position;
import chess.domain.direction.BasicDirection;
import chess.domain.direction.Direction;
import chess.domain.direction.DirectionGenerator;

public class WhitePawnMovingStrategy implements MovingStrategy {

	private static final int WHITE_PAWN_INITIAL_ROW = 2;

	@Override
	public boolean check(Position from, Position to) {
		Optional<? extends Direction> nullableDirection = DirectionGenerator.generateOfWhitePawn(from, to);
		if (nullableDirection.isEmpty()) {
			return false;
		}

		Direction direction = nullableDirection.get();
		int distance = from.calculateDistance(to);

		if (isFirstStartWithTwoStep(from, direction, distance)) {
			return true;
		}
		if (isDiagonalStep(direction, distance)) {
			return true;
		}
		return distance == 1;
	}

	private boolean isDiagonalStep(Direction direction, int distance) {
		return distance == 2 && direction != BasicDirection.NORTH;
	}

	private boolean isFirstStartWithTwoStep(Position from, Direction direction, int distance) {
		return distance == 2
			&& direction == BasicDirection.NORTH
			&& from.isSameRow(WHITE_PAWN_INITIAL_ROW);
	}
}
