package domain.strategy;

import java.util.Arrays;

public enum KnightDirection {
    UP_RIGHT(-2, 1),
    RIGHT_UP(-1, 2),
    RIGHT_DOWN(1, 2),
    DOWN_RIGHT(2, 1),
    DOWN_LEFT(2, -1),
    LEFT_DOWN(1, -2),
    LEFT_UP(-1, -2),
    UP_LEFT(-2, -1);

    private final int row;
    private final int col;

    KnightDirection(final int row, final int col) {
        this.row = row;
        this.col = col;
    }

    private boolean hasPosition(int row, int col) {
        return this.row == row && this.col == col;
    }

    public static boolean isExist(int row, int col) {
        return Arrays.stream(values())
                .anyMatch(knightDirection -> knightDirection.hasPosition(row, col));
    }
}
