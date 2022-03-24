package chess.domain.position;

import java.util.Arrays;

public enum Direction {

    RIGHT(0, 1),
    LEFT(0, -1),
    TOP(-1, 0),
    BOTTOM(1, 0),
    TOP_RIGHT(-1, 1),
    TOP_LEFT(-1, -1),
    BOTTOM_RIGHT(1, 1),
    BOTTOM_LEFT(1, -1)
    ;

    private final int row;
    private final int col;

    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static Direction of(Position source, Position target) {
        int rowWeight = calculateWeight(source.getRankIndex() - target.getRankIndex());
        int colWeight = calculateWeight(source.getFileIndex() - target.getFileIndex());

        return Arrays.stream(values())
                .filter(direction -> direction.row == rowWeight && direction.col == colWeight)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private static int calculateWeight(int value) {
        return Integer.compare(0, value);
    }

    public static boolean isDiagonal(Position source, Position target) {
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
