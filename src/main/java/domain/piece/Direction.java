package domain.piece;

public enum Direction {
    EAST(1,0),
    SOUTH_EAST(1,-1),
    SOUTH(0,-1),
    SOUTH_WEST(-1,-1),
    WEST(-1,0),
    NORTH_WEST(-1,1),
    NORTH(0,1),
    NORTH_EAST(1,1);

    private final int file;
    private final int rank;

    Direction(int file, int rank) {
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
