package domain;

public enum Direction {
    EAST(0, 1),
    WEST(0, -1),
    SOUTH(-1, 0),
    NORTH(1, 0),
    NORTHEAST(1, 1),
    NORTHWEST(1, -1),
    SOUTHEAST(-1, 1),
    SOUTHWEST(-1, -1);

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
