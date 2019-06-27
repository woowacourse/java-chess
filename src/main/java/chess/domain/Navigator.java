package chess.domain;

import chess.domain.direction.*;

import java.util.HashMap;
import java.util.Map;

public class Navigator {
    private final Direction direction;
    private final int moveCount;

    private static final Map<Double, Direction> DIRECTIONS = new HashMap<>();

    static {
        DIRECTIONS.put(Double.POSITIVE_INFINITY, VerticalDirection.getInstance());
        DIRECTIONS.put(0.0, HorizonDirection.getInstance());
        DIRECTIONS.put(1.0, RightDiagonalDirection.getInstance());
        DIRECTIONS.put(-1.0, LeftDiagonalDirection.getInstance());
        DIRECTIONS.put(2.0, VerticalRightKnightDirection.getInstance());
        DIRECTIONS.put(-2.0, VerticalLeftKnightDirection.getInstance());
        DIRECTIONS.put(0.5, HorizonLeftKnightDirection.getInstance());
        DIRECTIONS.put(-0.5, HorizonRightKnightDirection.getInstance());
    }

    public Navigator(Position startPosition, Position endPosition) {
        int rowDifference = endPosition.getRowDifference(startPosition);
        int columnDifference = endPosition.getColumnDifference(startPosition);
        this.direction = matchDirection(calculateInclination(rowDifference, columnDifference));
        this.moveCount = this.direction.matchMoveCount(rowDifference,columnDifference);
    }


    private double calculateInclination(int rowDifference, int columnDifference){
        if (columnDifference == 0) {
            return Double.POSITIVE_INFINITY;
        }

        return (double) rowDifference / columnDifference;
    }

    private Direction matchDirection(double inclination) {
        if (DIRECTIONS.get(inclination) != null) {
            return DIRECTIONS.get(inclination);
        }
        throw new IllegalArgumentException("불가능한 움직임입니다.");
    }

    public Position simulateMove(Board board, Position startPosition) {
        Position endPosition = startPosition;

        for (int i = 0; i < Math.abs(moveCount)-1; i++) {
            endPosition = direction.simulateUnitMove(board, startPosition, isReverse());
        }

        return endPosition;
    }

    public boolean isReverse() {
        return moveCount < 0;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
