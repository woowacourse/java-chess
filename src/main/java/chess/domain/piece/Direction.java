package chess.domain.piece;

import java.util.List;
import java.util.stream.IntStream;

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

    public static Direction findDirection(Position start, Position target) {
        return getEightStraightDirections().stream()
                .filter(direction -> findPositionInDirection(start, target, direction))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치로 가는 방향을 찾을 수 없습니다."));
    }

    private static boolean findPositionInDirection(Position start, Position target, Direction direction) {
        return IntStream.rangeClosed(1, start.calculateStraightDistance(target))
                .mapToObj(number -> start.createNextPosition(direction, number))
                .anyMatch(position -> position.equals(target));
    }

    public static List<Direction> getEightStraightDirections() {
        return List.of(NORTH, NORTHEAST, EAST, SOUTHEAST, SOUTH, SOUTHWEST, WEST, NORTHWEST);
    }

    public int getXDegree() {
        return xDegree;
    }

    public int getYDegree() {
        return yDegree;
    }
}
