package domain.directions;

public enum Direction {
    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1),
    NORTH(0, 1),
    NORTHEAST(1, 1),
    NORTHWEST(-1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    NORTHEAST_NORTH(1, 2),
    NORTHWEST_NORTH(-1, 2),
    NORTHWEST_WEST(-2, -1),
    SOUTHWEST_WEST(-2, 1),
    NORTHEAST_EAST(2, 1),
    SOUTHEAST_EAST(2, -1),
    SOUTHEAST_SOUTH(1, -2),
    SOUTHWEST_SOUTH(-1, -2),
    SOUTH_SOUTH(0, -2),
    NORTH_NORTH(0, 2);

    private final int file;
    private final int rank;

    Direction(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}
