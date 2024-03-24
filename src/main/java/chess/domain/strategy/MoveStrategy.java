package chess.domain.strategy;

import chess.domain.Position;

public interface MoveStrategy {
    boolean canMove(Position currentPosition, Position newPosition);
}
