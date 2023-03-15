package chess.domain;

public enum Direction {

    EAST(0, 1),
    WEST(0, -1),
    SOUTH(-1, 0),
    NORTH(1, 0),

    SOUTH_EAST(-1, 1),
    NORTH_EAST(1, 1),
    NORTH_WEST(1, -1),
    SOUTH_WEST(-1, -1),

    EAST_UP(1, 2),
    EAST_DOWN(-1, 2),
    WEST_UP(1, -2),
    WEST_DOWN(-1, -2),
    NORTH_RIGHT(2, 1),
    NORTH_LEFT(2, -1),
    SOUTH_RIGHT(-2, 1),
    SOUTH_LEFT(-2, -1);

    private final int rankChange;
    private final int fileChange;

    Direction(final int rankChange, final int fileChange) {
        this.rankChange = rankChange;
        this.fileChange = fileChange;
    }

    public int getRankChange() {
        return rankChange;
    }

    public int getFileChange() {
        return fileChange;
    }

}
