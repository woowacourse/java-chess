package chess.domain.piece;

import static java.lang.Math.*;

import java.util.Arrays;

public enum Direction {
    E(0, 1),
    S(-1, 0),
    W(0, -1),
    N(1, 0),
    NE(1, 1),
    SE(-1, 1),
    SW(-1, -1),
    NW(1, -1),
    NNE(2, 1),
    NEE(1, 2),
    SEE(-1, 2),
    SSE(-2, 1),
    SSW(-2, -1),
    SWW(-1, -2),
    NWW(1, -2),
    NNW(2, -1);

    private final int row;
    private final int column;

    Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Direction of(final int row, final int column) {
        return Arrays.stream(Direction.values())
            .filter(direction -> direction.row == row && direction.column == column)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 방향입니다."));
    }

    public static Direction calculate(int rowDifference, int columnDifference) {
        if (abs(rowDifference) == abs(columnDifference)) {
            return Direction.of(rowDifference / abs(rowDifference), columnDifference / abs(columnDifference));
        }
        if (rowDifference == 0 && columnDifference != 0) {
            return Direction.of(0, columnDifference / abs(columnDifference));
        }
        if (columnDifference == 0 && rowDifference != 0) {
            return Direction.of(rowDifference / abs(rowDifference), 0);
        }
        return Direction.of(rowDifference, columnDifference);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
