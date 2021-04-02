package chess.domain.piece;

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
    WWS(-2, -1);

    private final int xDegree;
    private final int yDegree;

    Direction(int xDegree, int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
    }

    public static Direction of(int x, int y) {
        return getDirectionOrElseThrow(x, y);
    }

    private static Direction getDirectionOrElseThrow(int x, int y) {
        return Arrays.stream(Direction.values())
            .filter(direction -> direction.getXDegree() == x && direction.getYDegree() == y)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 방향은 존재하지 않습니다."));
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
        return Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
    }

    public static List<Direction> blackPawnDirection() {
        return Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
    }

    public static boolean isDiagonalDirection(Direction direction) {
        return diagonalDirection().contains(direction);
    }

    public static boolean isLinearDirection(Direction direction) {
        return linearDirection().contains(direction);
    }

    public int getXDegree() {
        return xDegree;
    }

    public int getYDegree() {
        return yDegree;
    }

    public double getUnit() {
        return Math.sqrt(xDegree * xDegree + yDegree * yDegree);
    }
}
