package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Queen implements Movable {

    @Override
    public List<Position> findMovablePositions(final Position position) {
        int sourceX = position.getX();
        int sourceY = position.getY();

        List<Position> movablePositions = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (isMovableDestination(sourceX, sourceY, i, j)) {
                    movablePositions.add(new Position(i, j));
                }
            }
        }
        return movablePositions;
    }

    private boolean isMovableDestination(final int sourceX, final int sourceY, final int destX, final int destY) {
        return isDestinationInRange(sourceX, sourceY, destX, destY) && isNotSource(sourceX, sourceY, destX, destY);
    }

    private boolean isDestinationInRange(final int sourceX, final int sourceY, final int destX, final int destY) {
        return isForwardBackLeftRight(sourceX, sourceY, destX, destY) || isDiagonal(sourceX, sourceY, destX, destY);
    }

    private boolean isForwardBackLeftRight(final int sourceX, final int sourceY, final int destX, final int destY) {
        return sourceY == destY || sourceX == destX;
    }

    private boolean isDiagonal(final int sourceX, final int sourceY, final int destX, final int destY) {
        return Math.abs(sourceX - destX) == Math.abs(sourceY - destY);
    }

    private boolean isNotSource(final int sourceX, final int sourceY, final int destX, final int destY) {
        return sourceX != destX || sourceY != destY;
    }

}
