package chess.domain;

import chess.domain.direction.*;

import java.util.HashMap;
import java.util.Map;

public class Navigator {
    private final Direction direction;
    private final int moveCount;

    private static final Map<Double, Direction> DIRECTIONS = new HashMap<>();

    static {
        DIRECTIONS.put(Double.POSITIVE_INFINITY, new VerticalDirection());
        DIRECTIONS.put(0.0, new HorizonDirection());
        DIRECTIONS.put(1.0, new RithtDiagonalDirection());
        DIRECTIONS.put(-1.0, new LeftDiagonalDirection());
        DIRECTIONS.put(2.0, new VerticalRightKnightDirection());
        DIRECTIONS.put(-2.0, new VerticalLeftKnightDirection());
        DIRECTIONS.put(0.5, new HorizonLeftKnightDirection());
        DIRECTIONS.put(-0.5, new HorizonRightKnightDirection());
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
}
