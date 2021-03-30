package chess.domain.board;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0, -1),
    NORTHEAST(1, -1),
    EAST(1, 0),
    SOUTHEAST(1, 1),
    SOUTH(0, 1),
    SOUTHWEST(-1, 1),
    WEST(-1, 0),
    NORTHWEST(-1, -1),

    NNE(1, -2),
    NNW(-1, -2),
    SSE(1, 2),
    SSW(-1, 2),
    EEN(2, -1),
    EES(2, 1),
    WWN(-2, -1),
    WWS(-2, 1);

    private static final String SAME_TARGET = "[ERROR] 현재 위치와 같은 곳으로 이동할 수 없습니다.";
    public static final String NOT_MOVABLE_DIRECTION = "[ERROR] 이동할 수 없는 방향입니다.";

    private final int xDegree;
    private final int yDegree;

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
        return Arrays.asList(NORTH, NORTHWEST, NORTHEAST);
    }

    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(SOUTH, SOUTHWEST, SOUTHEAST);
    }

    public static Direction findDirection(Position source, Position target) {
        int xGap = gap(source.getColumnAsIndex(), target.getColumnAsIndex());
        int yGap = gap(source.getRowAsIndex(), target.getRowAsIndex());
        if (source == target) {
            throw new IllegalArgumentException(SAME_TARGET);
        }
        if (xGap == 0) {
            yGap /= Math.abs(yGap);
        }
        if (yGap == 0) {
            xGap /= Math.abs(xGap);
        }
        if (xGap != 0 && yGap != 0) {
            int divisor = Math.min(Math.abs(xGap), Math.abs(yGap));
            xGap /= divisor;
            yGap /= divisor;
        }

        int finalXGap = xGap;
        int finalYGap = yGap;
        return Arrays.stream(Direction.values())
            .filter(direction -> direction.is(finalXGap, finalYGap))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(NOT_MOVABLE_DIRECTION));
    }

    private static int gap(int source, int target) {
        return target - source;
    }

    private boolean is(int x, int y) {
        return this.xDegree == x && this.yDegree == y;
    }
}
