package chess.domain.strategy;

import chess.domain.position.Position;

public final class StraightMove implements MoveStrategy {

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return from.calculateHorizontalDistance(to) == 0 || from.calculateVerticalDistance(to) == 0;
    }
}
