package chess.domain;

import chess.domain.direction.*;

import java.util.HashMap;
import java.util.Map;

public class Navigator {
    private static final Map<Double, Direction> DIRECTIONS = new HashMap<>();
    private static final double VERTICAL_INCLINATION = Double.POSITIVE_INFINITY;
    private static final double HORIZON_INCLINATION = 0.0;
    private static final double RINGT_DIAGONAL_INCLINATION = 1.0;
    private static final double LEFT_DIAGONAL_INCLINATION = -1.0;
    private static final double VERTICAL_RIGHT_KNIGHT_INCLINATION = 2.0;
    private static final double VERTICAL_LEFT_KNIGHT_INCLINATION = -2.0;
    private static final double HORIZON_LEFT_KNIGHT_INCLINATION = 0.5;
    private static final double HORIZON_RIGHT_KNIGHT_INCLINATION = -0.5;

    static {
        DIRECTIONS.put(VERTICAL_INCLINATION, VerticalDirection.getInstance());
        DIRECTIONS.put(HORIZON_INCLINATION, HorizonDirection.getInstance());
        DIRECTIONS.put(RINGT_DIAGONAL_INCLINATION, RightDiagonalDirection.getInstance());
        DIRECTIONS.put(LEFT_DIAGONAL_INCLINATION, LeftDiagonalDirection.getInstance());
        DIRECTIONS.put(VERTICAL_RIGHT_KNIGHT_INCLINATION, VerticalRightKnightDirection.getInstance());
        DIRECTIONS.put(VERTICAL_LEFT_KNIGHT_INCLINATION, VerticalLeftKnightDirection.getInstance());
        DIRECTIONS.put(HORIZON_LEFT_KNIGHT_INCLINATION, HorizonLeftKnightDirection.getInstance());
        DIRECTIONS.put(HORIZON_RIGHT_KNIGHT_INCLINATION, HorizonRightKnightDirection.getInstance());
    }

    private final Direction direction;
    private final int moveCount;

    public Navigator(Position startPosition, Position endPosition) {
        int rowDifference = endPosition.getRowDifference(startPosition);
        int columnDifference = endPosition.getColumnDifference(startPosition);
        this.direction = matchDirection(calculateInclination(rowDifference, columnDifference));
        this.moveCount = this.direction.matchMoveCount(rowDifference, columnDifference);
    }

    private double calculateInclination(int rowDifference, int columnDifference) {
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

        for (int i = 0; i < Math.abs(moveCount) - 1; i++) {
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
