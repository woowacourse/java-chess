package chess.domain.position;

public enum Direction {
    // TODO NSWE가 아니라 DOWN, LEFT 등의 표현으로 바꾸기
    N(-1, 0),
    S(1, 0),
    W(0, -1),
    E(0, 1),
    NE(-1, 1),
    NW(-1, -1),
    SE(1, 1),
    SW(1, -1),
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
