package chess.domain;

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

    private final int fileDisplacement;
    private final int rankDisplacement;

    Direction(final int fileDisplacement, final int rankDisplacement) {
        this.fileDisplacement = fileDisplacement;
        this.rankDisplacement = rankDisplacement;
    }

    public int nextFile(int currentFile) {
        return currentFile + fileDisplacement;
    }

    public int nextRank(int currentRank) {
        return currentRank + rankDisplacement;
    }

}
