package chess.domain.pieces;

import chess.domain.Point;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0, 1),
    NORTHEAST(1, 1),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    SOUTH(0, -1),
    SOUTHWEST(-1, -1),
    WEST(-1, 0),
    NORTHWEST(-1, 1),

    NNE(1, 2),
    NNW(-1, 2),
    SSE(1, -2),
    SSW(-1, -2),
    EEN(2, 1),
    EES(2, -1),
    WWN(-2, 1),
    WWS(-2, -1),

    WHITEPAWNTWOSTEP(0, 2),
    BLACKPAWNTWOSTEP(0, -2);

    private int xDegree;
    private int yDegree;

    Direction(int xDegree, int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
    }

    public int getXDegree() {
        return xDegree;
    }

    public int getYDegree() {
        return yDegree;
    }

    public static Direction of(Point source, Point target) {
        int xDegree = target.minusX(source);
        int yDegree = target.minusY(source);

        if (xDegree == 0 && yDegree > 0) {
            return NORTH;
        }
        if (xDegree == 0 && yDegree < 0) {
            return SOUTH;
        }
        if (xDegree > 0 && yDegree == 0) {
            return EAST;
        }
        if (xDegree < 0 && yDegree == 0) {
            return WEST;
        }
        if (source.isValidIncline(target) && xDegree > 0 && yDegree > 0) {
            return NORTHEAST;
        }
        if (source.isValidIncline(target) && xDegree > 0 && yDegree < 0) {
            return SOUTHEAST;
        }
        if (source.isValidIncline(target) && xDegree < 0 && yDegree < 0) {
            return SOUTHWEST;
        }
        if (source.isValidIncline(target) && xDegree < 0 && yDegree > 0) {
            return NORTHWEST;
        }
        throw new IllegalArgumentException("올바른 방향이 아닙니다!");
    }

    public static List<Direction> linearDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<Direction> diagonalDirection() {
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> everyDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static List<Direction> knightDirection() {
        return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    }

    public static List<Direction> whitePawnDirection() {
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST, WHITEPAWNTWOSTEP);
    }

    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST, BLACKPAWNTWOSTEP);
    }

    public static List<Direction> pawnDirection(Color color) {
        if (color == Color.WHITE) {
            return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
        }
        return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
    }

    public static List<Direction> pawnFirstTurnDirection(Color color) {
        if (color == Color.WHITE) {
            return Arrays.asList(WHITEPAWNTWOSTEP);
        }
        return Arrays.asList(BLACKPAWNTWOSTEP);
    }

    public static List<Direction> pawnMoveDirection(Color color) {
        if (color == Color.WHITE) {
            return Arrays.asList(NORTH);
        }
        return Arrays.asList(SOUTH);
    }

    public static List<Direction> pawnAttackDirection(Color color) {
        if (color == Color.WHITE) {
            return Arrays.asList(NORTHEAST, NORTHWEST);
        }
        return Arrays.asList(SOUTHEAST, SOUTHWEST);
    }
}