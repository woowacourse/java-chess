package chess.domain.position.directionstrategy;

import chess.domain.position.Position;

public class HorizontalDirection implements DirectionStrategy {

    @Override
    public boolean checkDirection(Position from, Position to) {
        return from.isSameYAxis(to);
    }
}
