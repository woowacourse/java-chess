package domain;


public enum Direction {
    NONE(Integer.MAX_VALUE, Integer.MAX_VALUE),

    NORTH(0, 1),
    SOUTH(0, -1),
    EAST(1, 0),
    WEST(-1, 0),

    NORTH_NORTH(0, 2),
    SOUTH_SOUTH(0, -2),
    EAST_EAST(2, 0),
    WEST_WEST(-2, 0),

    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),
    SOUTH_EAST(1, -1),
    SOUTH_WEST(-1, -1);

    private final int row;
    private final int column;

    Direction(final int row, final int column) {
        this.row = row;
        this.column = column;
    }

    public static Direction from(final int fileSub, final int rankSub) {
        for (final Direction value : Direction.values()) {
            if (value.row == fileSub && value.column == rankSub) {
                return value;
            }
        }
        return Direction.NONE;
    }

    public int row() {
        return row;
    }

    public int column() {
        return column;
    }
}
