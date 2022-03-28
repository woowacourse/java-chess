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

    public static boolean isPawnMoving(final Position from, final Position to) {
        // TODO: 추가
        return true;
    }

    public static boolean isRookMoving(final Position from, final Position to) {
        return isVertical(from, to) || isHorizontal(from, to);
    }

    public static boolean isKnightMoving(final Position from, final Position to) {
        return knightStep().contains(getDirection(from, to));
    }

    public static boolean isBishopMoving(final Position from, final Position to) {
        return isDiagonal(from, to);
    }

    public static boolean isQueenMoving(final Position from, final Position to) {
        return isVertical(from, to) || isHorizontal(from, to) || isDiagonal(from, to);
    }

    public static boolean isKingMoving(final Position from, final Position to) {
        return kingStep().contains(getDirection(from, to));
    }

    // TODO: public -> private
    public static boolean isVertical(final Position from, final Position to) {
        return from.getCoordinateXOrder() == to.getCoordinateXOrder();
    }

    private static boolean isHorizontal(final Position from, final Position to) {
        return from.getCoordinateY() == to.getCoordinateY();
    }

    // TODO: public -> private
    public static boolean isDiagonal(final Position from, final Position to) {
        return Math.abs(from.getCoordinateXOrder() - to.getCoordinateXOrder()) == Math.abs(from.getCoordinateY() - to.getCoordinateY());
    }

    private static Direction getDirection(final Position from, final Position to) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.x == (from.getCoordinateXOrder() - to.getCoordinateXOrder()))
                .filter(direction -> direction.y == (from.getCoordinateY() - to.getCoordinateY()))
                .findFirst()
                .orElse(INVALID);
    }

    private static List<Direction> knightStep() {
        return Arrays.asList(LLU, RRU, LUU, RUU, LLD, RRD, LDD, RDD);
    }

    private static List<Direction> kingStep() {
        return Arrays.asList(LEFT, RIGHT, UP, DOWN, LEFT_AND_UP, RIGHT_AND_UP, LEFT_AND_DOWN, RIGHT_AND_DOWN);
    }
}
