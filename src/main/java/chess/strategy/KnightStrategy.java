package chess.strategy;

import chess.domain.Position;

public class KnightStrategy implements MoveStrategy{
    @Override
    public boolean isMovable(Position source, Position target) {
        int xDistance = source.getXDistanceTo(target);
        int yDistance = source.getYDistanceTo(target);
        return isInKnightRange(xDistance, yDistance);
    }

    private static boolean isInKnightRange(int xDistance, int yDistance) {
        return (xDistance == 2 && yDistance == 1) || (xDistance == 1 && yDistance == 2);
    }
}
