package chess.domain.piece;

import static java.lang.Math.abs;

import java.util.Arrays;

public enum Direction {

    E(1, 0),
    S(0, -1),
    W(-1, 0),
    N(0, 1),
    NE(1, 1),
    SE(1, -1),
    SW(-1, -1),
    NW(-1, 1),
    NNE(1, 2),
    NEE(2, 1),
    SEE(2, -1),
    SSE(1, -2),
    SSW(-1, -2),
    SWW(-2, -1),
    NWW(-2, 1),
    NNW(-1, 2);

    private final int column;
    private final int row;

    Direction(final int column, final int row) {
        this.column = column;
        this.row = row;
    }

    public static Direction of(final int column, final int row) {
        return Arrays.stream(Direction.values())
                .filter(direction -> direction.column == column && direction.row == row)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 방향입니다."));
    }

    public static Direction calculate(final int columnDifference, final int rowDifference) {
        if (abs(columnDifference) == abs(rowDifference)) {
            return Direction.of(columnDifference / abs(columnDifference), rowDifference / abs(rowDifference));
        }
        if (columnDifference != 0 && rowDifference == 0) {
            return Direction.of(columnDifference / abs(columnDifference), 0);
        }
        if (columnDifference == 0 && rowDifference != 0) {
            return Direction.of(0, rowDifference / abs(rowDifference));
        }
        return Direction.of(columnDifference, rowDifference);
    }

    public boolean isDiagonal() {
        return this == NE || this == SE || this == SW || this == NW;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
