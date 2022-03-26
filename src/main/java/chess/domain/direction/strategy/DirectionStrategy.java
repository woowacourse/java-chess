package chess.domain.direction.strategy;

import java.util.Optional;

import chess.domain.position.Position;
import chess.domain.direction.Direction;

public interface DirectionStrategy {

	Optional<? extends Direction> find(Position from, Position to);
}
