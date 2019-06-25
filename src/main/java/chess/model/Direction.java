package chess.model;

public enum Direction {
    NW(-1, 1),
    N(0, 1),
    NE(1, 1),
    E(1, 0),
    SE(1, -1),
    S(0, -1),
    SW(-1, -1),
    W(-1, 0),
    SSE(1, -2),
    SSW(-1, -2),
    SEE(2, -1),
    SWW(-2, -1),
    NNE(1, 2),
    NNW(-1, 2),
    NEE(2, 1),
    NWW(-2, 1);

    private int rowShiftUnit;
    private int columnShiftUnit;

    Direction(int rowShiftUnit, int columnShiftUnit) {
        this.rowShiftUnit = rowShiftUnit;
        this.columnShiftUnit = columnShiftUnit;
    }

    public int getColumnShiftUnit() {
        return columnShiftUnit;
    }

    public int getRowShiftUnit() {
        return rowShiftUnit;
    }
}
