package chess.strategy;

import chess.domain.Position;

public class DiagonalStrategy implements MoveStrategy{
    @Override
    public boolean isMovable(Position source, Position target) {
        int xDistance = source.getXDistanceTo(target);
        int yDistance = source.getYDistanceTo(target);
        return yDistance == xDistance;
    }
}
