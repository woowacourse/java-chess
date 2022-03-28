package chess.domain.position;

import java.util.Arrays;
import java.util.List;

public enum Direction {

    LEFT(-1, 0),
    RIGHT(1, 0),
    UP(0, 1),
    DOWN(0, -1),
    LEFT_AND_UP(-1, 1),
    LEFT_AND_DOWN(-1, -1),
    RIGHT_AND_UP(1, 1),
    RIGHT_AND_DOWN(1, -1),

    UP_AND_UP(0, 2),
    DOWN_AND_DOWN(0, -2),

    LLU(-2, 1),
    RRU(2, 1),
    LUU(-1, 2),
    RUU(1, 2),
    LLD(-2, -1),
    RRD(2, -1),
    LDD(-1, -2),
    RDD(1, -2),

    INVALID(0, 0),
    ;

    private final int x;
    private final int y;

    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean isVertical(final Position from, final Position to) {
        return from.getCoordinateXOrder() == to.getCoordinateXOrder();
    }

    public static boolean isHorizontal(final Position from, final Position to) {
        return from.getCoordinateY() == to.getCoordinateY();
    }

    public static boolean isDiagonal(final Position from, final Position to) {
        return Math.abs(from.getCoordinateXOrder() - to.getCoordinateXOrder()) == Math.abs(from.getCoordinateY() - to.getCoordinateY());
    }

    public static List<Direction> whitePawnStartingForwardStep() {
        return Arrays.asList(UP, UP_AND_UP);
    }

    public static List<Direction> whitePawnForwardStep() {
        return Arrays.asList(UP);
    }

    public static List<Direction> whitePawnDiagonalStep() {
        return Arrays.asList(LEFT_AND_UP, RIGHT_AND_UP);
    }

    public static List<Direction> blackPawnStartingForwardStep() {
        return Arrays.asList(DOWN, DOWN_AND_DOWN);
    }

    public static List<Direction> blackPawnForwardStep() {
        return Arrays.asList(DOWN);
    }

    public static List<Direction> blackPawnDiagonalStep() {
        return Arrays.asList(LEFT_AND_DOWN, RIGHT_AND_DOWN);
    }

    public static List<Direction> knightStep() {
        return Arrays.asList(LLU, RRU, LUU, RUU, LLD, RRD, LDD, RDD);
    }

    public static List<Direction> kingStep() {
        return Arrays.asList(LEFT, RIGHT, UP, DOWN, LEFT_AND_UP, RIGHT_AND_UP, LEFT_AND_DOWN, RIGHT_AND_DOWN);
    }

    public static Direction getDirection(final Position from, final Position to) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.x == (to.getCoordinateXOrder() - from.getCoordinateXOrder()))
                .filter(direction -> direction.y == (to.getCoordinateY() - from.getCoordinateY()))
                .findFirst()
                .orElse(INVALID);
    }
}
