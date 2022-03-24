package chess.domain.piece.strategy;

import chess.domain.Position;
import chess.domain.direction.DirectionGenerator;

public class QueenMovingStrategy implements MovingStrategy {

	@Override
	public boolean check(Position from, Position to) {
		return DirectionGenerator.generateOfRoyal(from, to).isPresent();
	}
}
