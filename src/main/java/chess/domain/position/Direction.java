package chess.domain.position;

public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1),
    UP_RIGHT(-1, 1),
    UP_LEFT(-1, -1),
    DOWN_RIGHT(1, 1),
    DOWN_LEFT(1, -1),
    ;

    private final int rowWeight;
    private final int columnWeight;

    Direction(int rowWeight, int columnWeight) {
        this.rowWeight = rowWeight;
        this.columnWeight = columnWeight;
    }

    public int getRowWeight() {
        return rowWeight;
    }

    public int getColumnWeight() {
        return columnWeight;
    }
}
