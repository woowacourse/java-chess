package chess.domain.strategy;

import chess.domain.position.Position;

public final class NonSlidingMove implements MoveStrategy {

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return from.calculateVerticalDistance(to) <= 1 && from.calculateHorizontalDistance(to) <= 1;
    }
}
