package chess.domain.strategy.direction;

import chess.domain.position.Position;

import java.util.List;

public interface DirectionStrategy {
    List<Position> findPath(Position source, Position target);
}