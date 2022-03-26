package domain.directions;

public enum Direction {
    EAST(0, 1),
    WEST(0, -1),
    SOUTH(-1, 0),
    NORTH(1, 0),
    NORTHEAST(1, 1),
    NORTHWEST(1, -1),
    SOUTHEAST(-1, 1),
    SOUTHWEST(-1, -1),
    NORTHEAST_NORTH(2, 1),
    NORTHWEST_NORTH(2, -1),
    NORTHWEST_WEST(-1, -2),
    SOUTHWEST_WEST(1, -2),
    NORTHEAST_EAST(1, 2),
    SOUTHEAST_EAST(-1, 2),
    SOUTHEAST_SOUTH(-2, 1),
    SOUTHWEST_SOUTH(-2, -1),
    SOUTH_SOUTH(-2, 0),
    NORTH_NORTH(2, 0);

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
