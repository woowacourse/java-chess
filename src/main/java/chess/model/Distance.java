package chess.model;

public class Distance {
    private final int fileDifference;
    private final int rankDifference;

    public Distance(final int fileDifference, final int rankDifference) {
        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
    }

    public boolean isForward() {
        if (fileDifference != 0) {
            return false;
        }
        return rankDifference > 0;
    }

    public boolean isDiagonalMovement() {
        if (isNotMoved()) {
            return false;
        }
        return Math.abs(fileDifference) == Math.abs(rankDifference);
    }

    public boolean isCrossMovement() {
        if (isNotMoved()) {
            return false;
        }
        return fileDifference == 0 || rankDifference == 0;
    }

    public boolean hasSame(int displacement) {
        if (isCrossMovement() || isDiagonalMovement()) {
            return Math.abs(fileDifference) == displacement || Math.abs(rankDifference) == displacement;
        }
        return Math.abs(fileDifference) + Math.abs(rankDifference) == displacement;
    }

    private boolean isNotMoved() {
        return fileDifference == 0 && rankDifference == 0;
    }

    public int getFileDifference() {
        return fileDifference;
    }

    public int getRankDifference() {
        return rankDifference;
    }
}
