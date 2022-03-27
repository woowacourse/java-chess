package chess.strategy;

import chess.domain.piece.Position;

@FunctionalInterface
public interface OccupiedChecker {

   boolean test(Position position);
}
