package chess.domain;

import java.util.Arrays;
import java.util.List;


public enum Direction {

    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1),
    LEFTUP(-1, 1),
    RIGHTUP(1, 1),
    LEFTDOWN(-1, -1),
    RIGHTDOWN(1, -1);

    private static final String NON_MOVABLE_DIRECTION = "[ERROR] 지정한 목적지는 갈 수 있는 방향이 존재하지 않습니다.";

    private final int xPoint;
    private final int yPoint;

    Direction(int xPoint, int yPoint) {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
    }

    public static Direction giveDirection(Position p1, Position p2) {
        return Arrays.stream(Direction.values())
            .filter(direction -> Math.atan2(direction.yPoint, direction.xPoint) == p1.getAngle(p2))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(NON_MOVABLE_DIRECTION));
    }

    public static Position step(Position from, Direction direction) {
        int xDifference = direction.xPoint;
        int yDifference = direction.yPoint;
        return from.getIncreasedOneStepPosition(xDifference, yDifference);
    }

    public static List<Direction> rowAndColumns() {
        return List.of(LEFT, RIGHT, UP, DOWN);
    }

    public static List<Direction> diagonals() {
        return List.of(LEFTUP, RIGHTUP, LEFTDOWN, RIGHTDOWN);
    }
}
