package chess.game;

import java.util.Arrays;

public enum Direction {

    N(0, 1),
    NE(1, 1),
    E(1, 0),
    SE(1, -1),
    S(0, -1),
    SW(-1, -1),
    W(-1, 0),
    NW(-1, 1),

    NNE(1, 2),
    ENE(2, 1),
    ESE(2, -1),
    SSE(1, -2),
    SSW(-1, -2),
    WSW(-2, -1),
    WNW(-2, 1),
    NNW(-1, 2);

    private static final double ONE_RADIAN = 180 / Math.PI;

    private final int column;
    private final int row;

    Direction(final int column, final int row) {
        this.column = column;
        this.row = row;
    }

    public static Direction find(final Position from, final Position to) {
        return Arrays.stream(Direction.values())
                .filter(value -> isEqualDirection(from, to, value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방향입니다."));
    }

    private static boolean isEqualDirection(final Position from, final Position to, final Direction value) {
        final int columnDistance = to.getColumnDistance(from);
        final int rowDistance = to.getRowDistance(from);
        return angle(value.column, value.row) == angle(columnDistance, rowDistance);
    }

    private static double angle(final int column, final int row) {
        return Math.atan2(column, row) * ONE_RADIAN;
    }

    public boolean isN() {
        return this == N;
    }

    public boolean isS() {
        return this == S;
    }

    public boolean isEqualTo(final int columnDistance, final int rowDistance) {
        return column == columnDistance && row == rowDistance;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}

