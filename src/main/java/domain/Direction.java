package domain;

public enum Direction {
    EAST(0, 1),
    WEST(0, -1),
    SOUTH(-1, 0),
    NORTH(1, 0);

    private final int row;
    private final int column;

    Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
