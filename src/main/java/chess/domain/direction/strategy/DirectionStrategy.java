package chess.domain.direction.strategy;

import chess.domain.direction.Direction;
import chess.domain.position.Position;

public interface DirectionStrategy {

	Direction find(Position from, Position to);
}
