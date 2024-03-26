package chess.domain.movement;

public enum UnitMovement {
    UP(0, 1),
    DOWN(0, -1),
    RIGHT(1, 0),
    LEFT(-1, 0),
    RIGHT_UP(RIGHT.fileDiff, UP.rankDiff),
    RIGHT_DOWN(RIGHT.fileDiff, DOWN.rankDiff),
    LEFT_UP(LEFT.fileDiff, UP.rankDiff),
    LEFT_DOWN(LEFT.fileDiff, DOWN.rankDiff),
    UP_UP_RIGHT(RIGHT.fileDiff, UP.rankDiff * 2),
    UP_UP_LEFT(LEFT.fileDiff, UP.rankDiff * 2),
    DOWN_DOWN_RIGHT(RIGHT.fileDiff, DOWN.rankDiff * 2),
    DOWN_DOWN_LEFT(LEFT.fileDiff, DOWN.rankDiff * 2),
    RIGHT_RIGHT_UP(RIGHT.fileDiff * 2, UP.rankDiff),
    RIGHT_RIGHT_DOWN(RIGHT.fileDiff * 2, DOWN.rankDiff),
    LEFT_LEFT_UP(LEFT.fileDiff * 2, UP.rankDiff),
    LEFT_LEFT_DOWN(LEFT.fileDiff * 2, DOWN.rankDiff);

    private final int fileDiff;
    private final int rankDiff;

    UnitMovement(int fileDiff, int rankDiff) {
        this.fileDiff = fileDiff;
        this.rankDiff = rankDiff;
    }

    public int getFileDiff() {
        return fileDiff;
    }

    public int getRankDiff() {
        return rankDiff;
    }
}
