package chess.domain.piece;

import java.util.Arrays;
import java.util.List;

import static chess.domain.piece.Color.WHITE;

public enum Direction {
    NORTH(-1, 0),
    NORTH_EAST(-1, 1),
    EAST(0, 1),
    SOUTH_EAST(1, 1),
    SOUTH(1, 0),
    SOUTH_WEST(1, -1),
    WEST(0, -1),
    NORTH_WEST(-1, -1),

    NORTH_NORTH_EAST(-2, 1),
    NORTH_NORTH_WEST(-2, -1),
    SOUTH_SOUTH_EAST(2, 1),
    SOUTH_SOUTH_WEST(2, 1),
    NORTH_WEST_WEST(-1, -2),
    NORTH_EAST_EAST(-1, 2),
    SOUTH_WEST_WEST(1, -2),
    SOUTH_EAST_EAST(1, 2);

    private final int rowDirection;
    private final int columnDirection;

    Direction(int rowDirection, int columnDirection) {
        this.rowDirection = rowDirection;
        this.columnDirection = columnDirection;
    }

    public static Direction createDirection(int initialRowDifference, int initialColumnDifference) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.rowDirection == initialRowDifference)
                .filter(direction -> direction.columnDirection == initialColumnDifference)
                .findAny()
                .orElse(createDirectionWithNormalization(initialRowDifference, initialColumnDifference));
    }

    private static Direction createDirectionWithNormalization(int initialRowDifference, int initialColumnDifference) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.rowDirection == normalizeDifference(initialRowDifference))
                .filter(direction -> direction.columnDirection == normalizeDifference(initialColumnDifference))
                .findAny()
                .orElseThrow(UnsupportedOperationException::new);
    }

    private static int normalizeDifference(int difference) {
        return Integer.compare(difference, 0);
    }

    public static boolean isNotPawnDirection(Direction direction, Color color) {
        if (color.isSameAs(WHITE)) {
            List<Direction> whitePawnDirection = Arrays.asList(NORTH, NORTH_EAST, NORTH_WEST);
            return !whitePawnDirection.contains(direction);
        }
        List<Direction> blackPawnDirection = Arrays.asList(SOUTH, SOUTH_EAST, SOUTH_WEST);
        return !blackPawnDirection.contains(direction);
    }

    public static boolean isNotRookDirection(Direction direction) {
        List<Direction> rookDirection = Arrays.asList(NORTH, SOUTH, EAST, WEST);
        return !rookDirection.contains(direction);
    }

    public static boolean isNotBishopDirection(Direction direction) {
        List<Direction> bishopDirection = Arrays.asList(SOUTH_EAST, SOUTH_WEST, NORTH_EAST, NORTH_WEST);
        return !bishopDirection.contains(direction);
    }

    public int addCurrentRow(int row) {
        return rowDirection + row;
    }

    public int addCurrentColumn(int column) {
        return columnDirection + column;
    }
}
