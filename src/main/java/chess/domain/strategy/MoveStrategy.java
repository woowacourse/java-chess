package chess.domain.strategy;

import chess.domain.Position;

public interface MoveStrategy {
    boolean canMove(final Position currentPosition, final Position newPosition);
}
