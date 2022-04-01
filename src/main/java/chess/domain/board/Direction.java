package chess.domain.board;

import chess.domain.position.Position;

import java.util.Arrays;
import java.util.List;

public enum Direction {
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1),

    NORTH_NORTH_EAST(1, 2),
    NORTH_NORTH_WEST(-1, 2),
    SOUTH_SOUTH_EAST(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),
    EAST_EAST_NORTH(2, 1),
    EAST_EAST_SOUTH(2, -1),
    WEST_WEST_NORTH(-2, 1),
    WEST_WEST_SOUTH(-2, -1);

    public static final List<Direction> LINEAR_DIRECTION = Arrays.asList(NORTH, EAST, SOUTH, WEST);
    public static final List<Direction> DIAGONAL_DIRECTION = Arrays.asList(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
    public static final List<Direction> EVERY_DIRECTION = Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
    public static final List<Direction> KNIGHT_DIRECTION = Arrays.asList(NORTH_NORTH_EAST, NORTH_NORTH_WEST, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST, EAST_EAST_NORTH, EAST_EAST_SOUTH, WEST_WEST_NORTH, WEST_WEST_SOUTH);
    public static final List<Direction> WHITE_PAWN_DIRECTION = Arrays.asList(NORTH, NORTH_EAST, NORTH_WEST);
    public static final List<Direction> BLACK_PAWN_DIRECTION = Arrays.asList(SOUTH, SOUTH_EAST, SOUTH_WEST);

    private final int x;
    private final int y;

    Direction(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction of(final Position from, final Position to) {
        final var xDifference = to.getFileOrder() - from.getFileOrder();
        final var yDifference = to.getRankNumber() - from.getRankNumber();

        if (isLine(xDifference, yDifference)) {
            return findDirection(calculateLineDifference(xDifference), calculateLineDifference(yDifference));
        }
        return findDirection(xDifference, yDifference);
    }

    private static boolean isLine(final int xDifference, final int yDifference) {
        return isDiagonal(xDifference, yDifference) || isHorizontalOrVertical(xDifference, yDifference);
    }

    private static boolean isDiagonal(final int xDifference, final int yDifference) {
        return Math.abs(xDifference) == Math.abs(yDifference);
    }

    private static boolean isHorizontalOrVertical(final int xDifference, final int yDifference) {
        return xDifference == 0 || yDifference == 0;
    }

    private static int calculateLineDifference(final int difference) {
        if (difference == 0) {
            return difference;
        }
        return difference / Math.abs(difference);
    }

    private static Direction findDirection(final int xDifference, final int yDifference) {
        return Arrays.stream(values())
                .filter(direction -> direction.x == xDifference && direction.y == yDifference)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("정의되지 않은 방향입니다."));
    }

    public boolean isVertical() {
        return this == Direction.NORTH || this == Direction.SOUTH;
    }

    public boolean isDiagonal() {
        return this == Direction.NORTH_EAST || this == Direction.SOUTH_EAST || this == Direction.SOUTH_WEST || this == Direction.NORTH_WEST;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
