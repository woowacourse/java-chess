package domain.strategy;

import domain.position.KnightDirection;
import domain.position.Position;

import java.util.Set;

public class KnightMoveStrategy implements MoveStrategy {
    @Override
    public boolean isMovable(final Position source, final Position destination, final Set<Position> piecePositions) {
        int rowDiff = destination.rowIndex() - source.rowIndex();
        int colDiff = destination.columnIndex() - source.columnIndex();

        return KnightDirection.isExist(rowDiff, colDiff);
    }
}
