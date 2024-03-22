package domain.strategy;

import domain.position.KnightMovementDirection;
import domain.position.Position;

import java.util.Set;

public class KnightMoveStrategy implements MoveStrategy {
    @Override
    public boolean isMovable(final Position source, final Position destination, final Set<Position> piecePositions) {
        return KnightMovementDirection.isMovableDirection(source, destination);
    }
}
