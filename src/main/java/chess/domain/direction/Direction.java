package chess.domain.direction;

import chess.domain.board.coordinate.Coordinate;
import java.util.Arrays;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),

    UP_LEFT(-1, 1),
    UP_RIGHT(1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(1, -1),

    UP_UP_LEFT(-1, 2),
    UP_UP_RIGHT(1, 2),
    DOWN_DOWN_LEFT(-1, -2),
    DOWN_DOWN_RIGHT(1, -2),
    LEFT_LEFT_UP(-2, 1),
    LEFT_LEFT_DOWN(-2, -1),
    RIGHT_RIGHT_UP(2, 1),
    RIGHT_RIGHT_DOWN(2, -1);

    private final int columnVector;
    private final int rowVector;

    Direction(int columnVector, int rowVector) {
        this.columnVector = columnVector;
        this.rowVector = rowVector;
    }

    public static Direction of(Coordinate from, Coordinate to) {
        int columnGap = from.columnGap(to);
        int rowGap = from.rowGap(to);

        return Arrays.stream(values())
                .filter(direction -> direction.columnVector == calculateVector(columnGap, rowGap))
                .filter(direction -> direction.rowVector == calculateVector(rowGap, columnGap))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방향입니다."));
    }

    private static int calculateVector(int gap, int otherGap) {
        if (gap == 0) {
            return gap;
        }
        if (otherGap == 0) {
            return gap / Math.abs(gap);
        }
        return gap / Math.min(Math.abs(gap), Math.abs(otherGap));
    }

    public boolean isVertical() {
        return this == UP || this == DOWN;
    }

    public int getColumnVector() {
        return columnVector;
    }

    public int getRowVector() {
        return rowVector;
    }
}
