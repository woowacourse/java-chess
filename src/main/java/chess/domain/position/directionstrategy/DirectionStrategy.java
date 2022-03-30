package chess.domain.position.directionstrategy;

import chess.domain.position.Position;

@FunctionalInterface
public interface DirectionStrategy {

    boolean checkDirection(Position from, Position to);
}
