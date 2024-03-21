package domain.piece.info;

public enum Direction {
    UP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    RIGHT(1, 0),

    UP_LEFT(-1, 1),
    UP_RIGHT(1, 1),
    DOWN_LEFT(-1, -1),
    DOWN_RIGHT(1, -1),

    UP_UP_RIGHT(1, 2),
    UP_UP_LEFT(-1, 2),
    UP_RIGHT_RIGHT(2, 1),
    UP_LEFT_LEFT(-2, 1),
    DOWN_DOWN_RIGHT(1, -2),
    DOWN_RIGHT_RIGHT(2, -1),
    DOWN_DOWN_LEFT(-1, -2),
    DOWN_LEFT_LEFT(-2, -1),

    UP_UP(0, 2),
    DOWN_DOWN(0, -2),
    ;

    private final int file;
    private final int rank;

    Direction(final int file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public int file() {
        return file;
    }

    public int rank() {
        return rank;
    }
}
