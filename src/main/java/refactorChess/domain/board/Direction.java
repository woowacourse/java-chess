package refactorChess.domain.board;

import java.util.Arrays;
import java.util.List;

public enum Direction {

    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0),

    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),
    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),

    NORTH_NORTH_EAST(1, 2),
    NORTH_NORTH_WEST(-1, 2),

    NORTH_EAST_EAST(2, 1),
    NORTH_WEST_WEST(-2, 1),

    SOUTH_SOUTH_RIGHT(1, -2),
    SOUTH_SOUTH_WEST(-1, -2),

    SOUTH_SOUTH_EAST(2, -1),
    SOUTH_WEST_WEST(-2, -1),
    ;

    public static final List<Direction> LINEAR_DIRECTION = Arrays.asList(
            NORTH, EAST, SOUTH, WEST);
    public static final List<Direction> DIAGONAL_DIRECTION = Arrays.asList(
            NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
    public static final List<Direction> EVERY_DIRECTION = Arrays.asList(
            NORTH, EAST, SOUTH, WEST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
    public static final List<Direction> KNIGHT_DIRECTION = Arrays.asList(
            NORTH_NORTH_EAST, NORTH_WEST_WEST, SOUTH_SOUTH_RIGHT, SOUTH_SOUTH_WEST, SOUTH_SOUTH_EAST, SOUTH_WEST_WEST);
    public static final List<Direction> WHITE_PAWN_DIRECTION = Arrays.asList(
            NORTH, NORTH_EAST, NORTH_WEST);
    public static final List<Direction> BLACK_PAWN_DIRECTION = Arrays.asList(
            SOUTH, SOUTH_EAST, SOUTH_WEST);

    private final int column;
    private final int row;

    Direction(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public static Direction of(int column, int row) {
        return Arrays.stream(values())
                .filter(direction -> direction.column == column && direction.row == row)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 방향입니다."));
    }

    public static Direction ofLinear(int column, int row) {
        if (Math.abs(column - row) != Math.abs(column + row)) {
            throw new IllegalArgumentException("직선 방향이 아닙니다.");
        }

        return distinctCompareToConvert(column, row);
    }

    public static Direction ofDiagonal(int column, int row) {
        if (Math.abs(column) - Math.abs(row) != 0) {
            throw new IllegalArgumentException("대각선 방향이 아닙니다.");
        }
        return of(column, row);
    }

    private static Direction distinctCompareToConvert(int column, int row) {
        return of(Integer.compare(column, 0), Integer.compare(row, 0));
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
