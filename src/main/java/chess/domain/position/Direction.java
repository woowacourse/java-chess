package chess.domain.position;

import java.util.Arrays;

public enum Direction {

    RIGHT(0, 1, false),
    LEFT(0, -1, false),
    TOP(-1, 0, false),
    BOTTOM(1, 0, false),
    TOP_RIGHT(-1, 1, true),
    TOP_LEFT(-1, -1, true),
    BOTTOM_RIGHT(1, 1, true),
    BOTTOM_LEFT(1, -1, true),
    NONE(0, 0, false);

    private final int row;
    private final int col;
    private final boolean isDiagonal;

    Direction(int row, int col, boolean isDiagonal) {
        this.row = row;
        this.col = col;
        this.isDiagonal = isDiagonal;
    }

    public static Direction of(Position source, Position target) {
        int rowWeight = calculateWeight(source.getRankIndex() - target.getRankIndex());
        int colWeight = calculateWeight(source.getFileIndex() - target.getFileIndex());

        return Arrays.stream(values())
                .filter(direction -> direction.row == rowWeight && direction.col == colWeight
                        && direction.isDiagonal == isDiagonal(source, target))
                .findFirst()
                .orElse(NONE);
    }

    private static int calculateWeight(int value) {
        return Integer.compare(0, value);
    }

    private static boolean isDiagonal(Position source, Position target) {
        int absRankIndex = Math.abs(source.getRankIndex() - target.getRankIndex());
        int absFileIndex = Math.abs(source.getFileIndex() - target.getFileIndex());

        return absRankIndex == absFileIndex;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
