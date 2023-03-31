package domain.path.direction;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

public enum Direction {
    RIGHT_UP(-1, -1, (columnDiff, rowDiff) ->
            columnDiff < 0 && rowDiff < 0 && Math.abs(columnDiff) == Math.abs(rowDiff)
    ),
    RIGHT_DOWN(-1, 1, (columnDiff, rowDiff) ->
            columnDiff < 0 && rowDiff > 0 && Math.abs(columnDiff) == Math.abs(rowDiff)
    ),
    LEFT_UP(1, -1, (columnDiff, rowDiff) ->
            columnDiff > 0 && rowDiff < 0 && Math.abs(columnDiff) == Math.abs(rowDiff)
    ),
    LEFT_DOWN(1, 1, (columnDiff, rowDiff) ->
            columnDiff > 0 && rowDiff > 0 && Math.abs(columnDiff) == Math.abs(rowDiff)
    ),
    UP(-1, 0, (columnDiff, rowDiff) ->
            columnDiff == 0 && rowDiff < 0
    ),
    DOWN(1, 0, (columnDiff, rowDiff) ->
            columnDiff == 0 && rowDiff > 0
    ),
    LEFT(0, -1, (columnDiff, rowDiff) ->
            columnDiff < 0 && rowDiff == 0
    ),
    RIGHT(0, 1, (columnDiff, rowDiff) ->
            columnDiff > 0 && rowDiff == 0
    ),
    UP_UP_LEFT(-1, 2, (columnDiff, rowDiff) ->
            columnDiff == -1 && rowDiff == 2
    ),
    UP_UP_RIGHT(1, 2, (columnDiff, rowDiff) ->
            columnDiff == 1 && rowDiff == 2
    ),
    DOWN_DOWN_LEFT(-1, -2, (columnDiff, rowDiff) ->
            columnDiff == -1 && rowDiff == -2
    ),
    DOWN_DOWN_RIGHT(1, -2, (columnDiff, rowDiff) ->
            columnDiff == 1 && rowDiff == -2
    ),
    LEFT_LEFT_UP(-2, 1, (columnDiff, rowDiff) ->
            columnDiff == -2 && rowDiff == 1
    ),
    LEFT_LEFT_DOWN(-2, -1, (columnDiff, rowDiff) ->
            columnDiff == -2 && rowDiff == -1
    ),
    RIGHT_RIGHT_UP(2, 1, (columnDiff, rowDiff) ->
            columnDiff == 2 && rowDiff == 1
    ),
    RIGHT_RIGHT_DOWN(2, -1, (columnDiff, rowDiff) ->
            columnDiff == 2 && rowDiff == -1
    );

    private final int columnDiff;
    private final int rowDiff;
    private final BiPredicate<Integer, Integer> condition;

    Direction(final int columnDiff, final int rowDiff, final BiPredicate<Integer, Integer> condition) {
        this.columnDiff = columnDiff;
        this.rowDiff = rowDiff;
        this.condition = condition;
    }

    public static Direction find(final int columnDiff, final int rowDiff) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.condition.test(columnDiff, rowDiff))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("이동할 수 없는 경로 입니다."));
    }

    public static List<Direction> pawnDirections() {
        return List.of(
                Direction.UP,
                Direction.DOWN,
                Direction.LEFT,
                Direction.RIGHT,
                Direction.LEFT_UP,
                Direction.LEFT_DOWN,
                Direction.RIGHT_UP,
                Direction.RIGHT_DOWN
        );
    }

    public static List<Direction> bishopDirections() {
        return List.of(
                Direction.LEFT_UP,
                Direction.LEFT_DOWN,
                Direction.RIGHT_UP,
                Direction.RIGHT_DOWN
        );
    }

    public static List<Direction> rookDirections() {
        return List.of(
                Direction.UP,
                Direction.DOWN,
                Direction.LEFT,
                Direction.RIGHT
        );
    }

    public static List<Direction> queenDirections() {
        return List.of(
                Direction.UP,
                Direction.DOWN,
                Direction.LEFT,
                Direction.RIGHT,
                Direction.LEFT_UP,
                Direction.LEFT_DOWN,
                Direction.RIGHT_UP,
                Direction.RIGHT_DOWN
        );
    }

    public static List<Direction> kingDirections() {
        return List.of(
                Direction.UP,
                Direction.DOWN,
                Direction.LEFT,
                Direction.RIGHT,
                Direction.LEFT_UP,
                Direction.LEFT_DOWN,
                Direction.RIGHT_UP,
                Direction.RIGHT_DOWN
        );
    }

    public static List<Direction> knightDirections() {
        return List.of(
                Direction.UP_UP_LEFT,
                Direction.UP_UP_RIGHT,
                Direction.DOWN_DOWN_LEFT,
                Direction.DOWN_DOWN_RIGHT,
                Direction.RIGHT_RIGHT_UP,
                Direction.RIGHT_RIGHT_DOWN,
                Direction.LEFT_LEFT_UP,
                Direction.LEFT_LEFT_DOWN
        );
    }

    public static List<Direction> emptyDirections() {
        return List.of();
    }

    public int getColDiff() {
        return columnDiff;
    }

    public int getRowDiff() {
        return rowDiff;
    }
}
