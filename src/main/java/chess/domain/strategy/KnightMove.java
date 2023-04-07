package chess.domain.strategy;

import chess.domain.position.Position;

public class KnightMove implements MoveStrategy {

    @Override
    public boolean isMovable(final Position from, final Position to) {
        final int horizontalDistance = from.calculateHorizontalDistance(to);
        final int verticalDistance = from.calculateVerticalDistance(to);

        return horizontalDistance + verticalDistance == 3
                && horizontalDistance != 0
                && verticalDistance != 0;
    }
}
