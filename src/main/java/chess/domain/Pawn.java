package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements Movable {

    private static final int FIRST_RANK = 2;

    @Override
    public List<Path> findMovablePaths(final Position position) {
        int sourceX = position.getX();
        int sourceY = position.getY();
//
//        if (isFirstMove(sourceX)) {
//            return findFirstMoveDestination(sourceX, sourceY);
//        }
//        return findMovePosition(sourceX, sourceY);

        return null;
    }

    private boolean isFirstMove(int sourceX) {
        return sourceX == FIRST_RANK;
    }

    private List<Position> findFirstMoveDestination(final int sourceX, final int sourceY) {
        List<Position> movablePositions = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (isFirstMovableDestination(sourceX, sourceY, i, j)) {
                    movablePositions.add(new Position(i, j));
                }
            }
        }
        return movablePositions;
    }

    private boolean isFirstMovableDestination(final int sourceX, final int sourceY, final int destX, final int destY) {
        return (destY == sourceY) && (destX - sourceX == 2 || destX - sourceX == 1);
    }

    private List<Position> findMovePosition(final int sourceX, final int sourceY) {
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
        return isDestinationInRange(sourceX, sourceY, destX, destY);
    }

    private boolean isDestinationInRange(final int sourceX, final int sourceY, final int destX, final int destY) {
        return isInForwardRange(sourceX, sourceY, destX, destY) || isInForwardDiagonalRange(sourceX, sourceY, destX,
                destY);
    }

    private boolean isInForwardRange(final int sourceX, final int sourceY, final int destX, final int destY) {
        return sourceY == destY && destX - sourceX == 1;
    }

    private boolean isInForwardDiagonalRange(final int sourceX, final int sourceY, final int destX, final int destY) {
        return destX - sourceX == 1 && Math.abs(destY - sourceY) == 1;
    }

    private boolean isNotSource(final int sourceX, final int sourceY, final int destX, final int destY) {
        return sourceX != destX || sourceY != destY;
    }
}
