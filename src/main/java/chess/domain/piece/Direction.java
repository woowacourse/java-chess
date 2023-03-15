package chess.domain.piece;

public enum Direction {

    UP(0, 1),
    UPRIGHT(1, 1),
    RIGHT(1, 0),
    DOWNRIGHT(1, -1),
    DOWN(0, -1),
    DOWNLEFT(-1, -1),
    LEFT(-1, 0),
    UPLEFT(-1, 1);


    Direction(final int fileDifference, final int rankDifference) {
        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
    }

    private final int fileDifference;
    private final int rankDifference;

    public int getFileDifference() {
        return fileDifference;
    }

    public int getRankDifference() {
        return rankDifference;
    }
}
