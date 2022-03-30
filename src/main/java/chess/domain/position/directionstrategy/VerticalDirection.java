package chess.domain.position.directionstrategy;

import chess.domain.position.Position;

public class VerticalDirection implements DirectionStrategy {

    @Override
    public boolean checkDirection(Position from, Position to) {
        return from.isSameXAxis(to);
    }
}
