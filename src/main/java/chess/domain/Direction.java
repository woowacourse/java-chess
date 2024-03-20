package chess.domain;

public enum Direction {
    N(-1, 0),
    NE(-1, 1),
    E(0, 1),
    SE(1, 1),
    S(1, 0),
    SW(1, -1),
    W(0, -1),
    NW(-1, -1);

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
