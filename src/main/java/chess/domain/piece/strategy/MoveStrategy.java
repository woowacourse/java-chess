package chess.domain.piece.strategy;

import chess.domain.position.Position;

@FunctionalInterface
public interface MoveStrategy {
    boolean canGoFrom(Position from, Position to);
}
