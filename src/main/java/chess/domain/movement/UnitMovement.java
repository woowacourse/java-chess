package chess.domain.movement;

public enum UnitMovement {
    UP(1, 0),
    DOWN(-1, 0),
    RIGHT(0, 1),
    LEFT(0, -1),
    RIGHT_UP(1, 1),
    RIGHT_DOWN(-1, 1),
    LEFT_UP(1, -1),
    LEFT_DOWN(-1, -1),
    UP_UP_RIGHT(2, 1),
    UP_UP_LEFT(2, -1),
    DOWN_DOWN_RIGHT(-2, 1),
    DOWN_DOWN_LEFT(-2, -1),
    RIGHT_RIGHT_UP(1, 2),
    RIGHT_RIGHT_DOWN(-1, 2),
    LEFT_LEFT_UP(1, -2),
    LEFT_LEFT_DOWN(-1, -2);

    private final int rankDiff;
    private final int fileDiff;

    UnitMovement(int rankDiff, int fileDiff) {
        this.rankDiff = rankDiff;
        this.fileDiff = fileDiff;
    }

    public int getRankDiff() {
        return rankDiff;
    }

    public int getFileDiff() {
        return fileDiff;
    }
}
