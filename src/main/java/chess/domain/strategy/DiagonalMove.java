package chess.domain.strategy;

import chess.domain.position.Position;

public final class DiagonalMove implements MoveStrategy {

    @Override
    public boolean isMovable(final Position from, final Position to) {
        return from.calculateHorizontalDistance(to) == from.calculateVerticalDistance(to);
    }
}
