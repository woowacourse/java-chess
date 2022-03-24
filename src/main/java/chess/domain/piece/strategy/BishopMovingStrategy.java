package chess.domain.piece.strategy;

import chess.domain.Position;
import chess.domain.direction.DirectionGenerator;

public class BishopMovingStrategy implements MovingStrategy {

	@Override
	public boolean check(Position from, Position to) {
		return DirectionGenerator.generateOfBishop(from, to)
			.isPresent();
	}
}
