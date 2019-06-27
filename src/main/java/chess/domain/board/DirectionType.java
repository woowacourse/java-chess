package chess.domain.board;

import java.util.Arrays;
import java.util.List;

public enum DirectionType {
    EAST(1, 0),
    SOUTHEAST(1, 1),
    WEST(-1, 0),
    NORTHWEST(-1, -1),
    SOUTH(0, 1),
    SOUTHWEST(-1, 1),
    NORTH(0, -1),
    NORTHEAST(1, -1);

    private int xDegree;
    private int yDegree;

    DirectionType(int xDegree, int yDegree) {
        this.xDegree = xDegree;
        this.yDegree = yDegree;
    }

    public static DirectionType valueOf(Point prev, Point next) {
        int xDistance = next.xDistance(prev);
        int yDistance = next.yDistance(prev);
        int size = prev.calculateMaxAbsoluteDistance(next);

        return Arrays.stream(values())
                .filter(value -> value.xDegree == xDistance / size)
                .filter(value -> value.yDegree == yDistance / size)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static List<DirectionType> linearDirection() {
        return Arrays.asList(NORTH, EAST, SOUTH, WEST);
    }

    public static List<DirectionType> diagonalDirection() {
        return Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
    }

    public static boolean isLinearDirection(DirectionType directionType) {
        return linearDirection().contains(directionType);
    }

    public static boolean isDiagonalDirection(DirectionType directionType) {
        return diagonalDirection().contains(directionType);
    }

    public int getxDegree() {
        return xDegree;
    }

    public int getyDegree() {
        return yDegree;
    }
}
