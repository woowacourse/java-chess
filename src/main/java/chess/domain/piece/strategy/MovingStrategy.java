package chess.domain.piece.strategy;

import chess.domain.board.Path;

@FunctionalInterface
public interface MovingStrategy {
    boolean isMovableWithin(Path path);
}
