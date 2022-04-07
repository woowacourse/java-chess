package chess.domain.piece;

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

    Direction(int column, int row) {
        this.column = column;
        this.row = row;
    }

    public static Direction calculate(int columnDifference, int rowDifference) {
        return Arrays.stream(Direction.values())
                .filter(direction -> isSameAngle(direction, columnDifference, rowDifference))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 방향입니다."));
    }

    private static boolean isSameAngle(Direction direction, int columnDifference, int rowDifference) {
        return Math.atan2(direction.row, direction.column) == Math.atan2(rowDifference, columnDifference);
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
