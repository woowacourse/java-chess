package chess.domain.path;

public enum Direction {

    EAST(1, 0),
    WEST(-1, 0),
    SOUTH(0, -1),
    NORTH(0, 1),

    SOUTH_EAST(1, -1),
    NORTH_EAST(1, 1),
    NORTH_WEST(-1, 1),
    SOUTH_WEST(-1, -1),

    EAST_UP(2, 1),
    EAST_DOWN(2, -1),
    WEST_UP(-2, 1),
    WEST_DOWN(-2, -1),
    NORTH_RIGHT(1, 2),
    NORTH_LEFT(-1, 2),
    SOUTH_RIGHT(1, -2),
    SOUTH_LEFT(-1, -2);

    private final int fileChange;
    private final int rankChange;

    Direction(final int fileChange, final int rankChange) {
        this.fileChange = fileChange;
        this.rankChange = rankChange;
    }

    public int moveFile(final int origin) {
        return origin +fileChange;
    }

    public int moveRank(final int origin) {
        return origin + rankChange;
    }

}
