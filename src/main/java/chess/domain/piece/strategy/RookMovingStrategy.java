package chess.domain.piece.strategy;

import chess.domain.Position;
import chess.domain.direction.DirectionGenerator;

public class RookMovingStrategy implements MovingStrategy {

	@Override
	public boolean check(Position from, Position to) {
		return DirectionGenerator.generateOfRook(from, to).isPresent();
	}
}
