package chess.domain.position.directionstrategy;

import chess.domain.position.Position;

public class DiagonalDirection implements DirectionStrategy {

    @Override
    public boolean checkDirection(Position from, Position to) {
        return from.isOnDiagonal(to);
    }
}
