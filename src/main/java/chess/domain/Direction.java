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
    RIGHTDOWN(1, -1),
    UUR(1, 2),
    UUL(-1, 2),
    RRU(2, 1),
    LLU(-2, 1),
    RRD(2, -1),
    DDR(1, -2),
    LLD(-2, -1),
    DDL(-1, -2);

    private static final String NON_MOVABLE_DIRECTION = "[ERROR] 지정한 목적지는 갈 수 있는 방향이 존재하지 않습니다.";

    private final int xPoint;
    private final int yPoint;

    Direction(int xPoint, int yPoint) {
        this.xPoint = xPoint;
        this.yPoint = yPoint;
    }

    public static Direction giveDirection(Position from, Position to) {
        return Arrays.stream(Direction.values())
                .filter(direction -> Math.atan2(direction.yPoint, direction.xPoint) == from.getAngle(to))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(NON_MOVABLE_DIRECTION));
    }

    public static Position step(Position from, Direction direction) {
        return from.getIncreasedOneStepPosition(direction.xPoint, direction.yPoint);
    }

    public static List<Direction> rowAndColumns() {
        return List.of(LEFT, RIGHT, UP, DOWN);
    }

    public static List<Direction> diagonals() {
        return List.of(LEFTUP, RIGHTUP, LEFTDOWN, RIGHTDOWN);
    }

    public static List<Direction> knightDirections() {
        return List.of(UUR, UUL, RRD, RRU, LLD, LLU, DDL, DDR);
    }
}
