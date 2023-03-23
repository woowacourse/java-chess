package domain.piece;

public enum Direction {
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1),
    NORTH(0, 1),
    NORTH_EAST(1, 1);

    private final int file;
    private final int rank;

    Direction(int file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Direction find(int file, int rank) {
        for (Direction direction : Direction.values()) {
            if (direction.file == file && direction.rank == rank) {
                return direction;
            }
        }
        throw new IllegalArgumentException("움직일 수 없는 경로입니다.");
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }
}
