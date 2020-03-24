package chess.domain.piece;

import chess.domain.board.Position;

@FunctionalInterface
public interface MovingStrategy {
    boolean test(Position start, Position end);
}
