package chess.strategy;

import chess.domain.position.Position;

@FunctionalInterface
public interface OccupiedChecker {

   boolean test(Position position);
}
