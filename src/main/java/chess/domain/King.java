package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class King implements Movable {

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
        return isInRange(destX, sourceX) && isInRange(destY, sourceY);
    }

    private boolean isInRange(final int targetValue, final int sourceValue) {
        return sourceValue - 1 <= targetValue && targetValue <= sourceValue + 1;
    }

    private boolean isNotSource(final int sourceX, final int sourceY, final int destX, final int destY) {
        return sourceX != destX || sourceY != destY;
    }

}
