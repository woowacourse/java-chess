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

		if (from.isSameRow(WHITE_PAWN_INITIAL_ROW) && direction == BasicDirection.NORTH) {
			return from.canReach(to, direction.getUnitPosition(), 2);
		}
		return from.canReach(to, direction.getUnitPosition(), 1);
	}
}
