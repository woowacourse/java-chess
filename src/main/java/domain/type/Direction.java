package domain.type;

import domain.Location;
import java.util.Arrays;
import java.util.function.BiPredicate;

public enum Direction {

    RIGHT_UP(1, 1, Direction::isRightUpDiagonal),
    RIGHT_DOWN(1, -1, Direction::isRightDownDiagonal),
    LEFT_UP(-1, 1, Direction::isLeftUpDiagonal),
    LEFT_DOWN(-1, -1, Direction::isLeftDownDiagonal),
    RIGHT(1, 0, Direction::isRight),
    LEFT(-1, 0, Direction::isLeft),
    DOWN(0, -1, Direction::isDown),
    UP(0, 1, Direction::isUp),
    ELSE(0, 0, (start, end) -> false);

    private final int columnDiff;

    private final int rowDiff;
    private final BiPredicate<Location, Location> condition;

    Direction(final int columnDiff, final int rowDiff, final BiPredicate<Location, Location> condition) {
        this.columnDiff = columnDiff;
        this.rowDiff = rowDiff;
        this.condition = condition;
    }

    private static boolean isRightUpDiagonal(final Location start, final Location end) {
        return start.isDiagonal(end)
            && end.isHigherThan(start)
            && end.isRightThan(start);
    }

    private static boolean isRightDownDiagonal(final Location start, final Location end) {
        return start.isDiagonal(end)
            && start.isHigherThan(end)
            && end.isRightThan(start);
    }

    private static boolean isLeftUpDiagonal(final Location start, final Location end) {
        return start.isDiagonal(end)
            && end.isHigherThan(start)
            && start.isRightThan(end);
    }

    private static boolean isLeftDownDiagonal(final Location start, final Location end) {
        return start.isDiagonal(end)
            && start.isHigherThan(end)
            && start.isRightThan(end);
    }

    private static boolean isRight(final Location start, final Location end) {
        return start.isSameRow(end) && end.isRightThan(start);
    }

    private static boolean isLeft(final Location start, final Location end) {
        return start.isSameRow(end) && start.isRightThan(end);
    }

    private static boolean isDown(final Location start, final Location end) {
        return start.isSameCol(end) && start.isHigherThan(end);
    }

    private static boolean isUp(final Location start, final Location end) {
        return start.isSameCol(end) && end.isHigherThan(start);
    }

    public static Direction find(final Location start, final Location end) {
        return Arrays.stream(Direction.values())
            .filter(direction -> direction.condition.test(start, end))
            .findAny()
            .orElse(ELSE);
    }

    public int getColumnDiff() {
        return columnDiff;
    }

    public int getRowDiff() {
        return rowDiff;
    }
}
