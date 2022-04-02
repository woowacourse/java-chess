package chess.domain;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0, 1),
    NORTHEAST(1, 1),
    NORTH_TWO_STEP(0, 2),
    EAST(1, 0),
    SOUTHEAST(1, -1),
    SOUTH(0, -1),
    SOUTHWEST(-1, -1),
    SOUTH_TWO_STEP(0, -2),
    WEST(-1, 0),
    NORTHWEST(-1, 1),

    NORTH_NORTH_EAST(1, 2),
    NORTH_NORTH_WEST(-1, 2),
    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    EAST_EAST_NORTH(2, 1),
    EAST_EAST_SOUTH(2, -1),
    WEST_WEST_NORTH(-2, 1),
    WEST_WEST_SOUTH(-2, -1);

    private final int xDegree;
    private final int yDegree;

    Direction(int xDegree, int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
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
        return Arrays.asList(NORTH_NORTH_EAST, NORTH_NORTH_WEST, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST, EAST_EAST_NORTH,
                EAST_EAST_SOUTH, WEST_WEST_NORTH, WEST_WEST_SOUTH);
    }

    public static List<Direction> whitePawnDirection(boolean isFirstTurn) {
        if (isFirstTurn) {
            return Arrays.asList(NORTH, NORTHEAST, NORTHWEST, NORTH_TWO_STEP);
        }
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    }

    public static List<Direction> blackPawnDirection(boolean isFirstTurn) {
        if (isFirstTurn) {
            return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST, SOUTH_TWO_STEP);
        }
        return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
    }

    public static Direction findMatchDirection(int colDiff, int rowDiff, List<Direction> directions) {
        int gcd = Math.abs(gcdOrNotZero(colDiff, rowDiff));
        return directions.stream()
                .filter(direction -> isMatch(colDiff, rowDiff, gcd, direction))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치로 말이 움직일 수 없습니다."));
    }

    private static int gcdOrNotZero(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcdOrNotZero(b, a % b);
    }

    private static boolean isMatch(int colDiff, int rowDiff, int gcd, Direction direction) {
        return direction.xDegree == colDiff / gcd && direction.yDegree == rowDiff / gcd;
    }

    public int getYDegree() {
        return yDegree;
    }

    public int getXDegree() {
        return xDegree;
    }
}
