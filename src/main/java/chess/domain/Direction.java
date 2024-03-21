package chess.domain;

public enum Direction {
    N(-1, 0),
    E(0, 1),
    S(1, 0),
    W(0, -1),

    NE(-1, 1),
    SE(1, 1),
    SW(1, -1),
    NW(-1, -1),

    NNE(-2, 1),
    ENE(-1, 2),
    ESE(1, 2),
    SSE(2, 1),
    SSW(2, -1),
    WSW(1, -2),
    WNW(-1, -2),
    NNW(-2, -1);

    private final int rowDirection;
    private final int columnDirection;

    Direction(int rowDirection, int columnDirection) {
        this.rowDirection = rowDirection;
        this.columnDirection = columnDirection;
    }

    public int calculateRowDistance(int weight) {
        return rowDirection * weight;
    }

    public int calculateColumnDistance(int weight) {
        return columnDirection * weight;
    }
}
