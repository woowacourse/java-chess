package chess.domain.position;

public enum Direction {
    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1),
    NORTH(0, 1),
    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1),
    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1);

    private final int columnValue;
    private final int rowValue;

    Direction(int columnValue, int rowValue) {
        this.columnValue = columnValue;
        this.rowValue = rowValue;
    }

    public int getColumnValue() {
        return columnValue;
    }

    public int getRowValue() {
        return rowValue;
    }
}
