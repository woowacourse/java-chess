package chess.domain.piece.strategy;

public enum DiagonalDirection {

    UP_RIGHT(1, 1),
    DOWN_RIGHT(1, -1),
    DOWN_LEFT(-1, -1),
    UP_LEFT(-1, 1);

    private final int fileDifference;
    private final int rankDifference;

    DiagonalDirection(final int fileDifference, final int rankDifference) {
        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
    }

    public static DiagonalDirection find(final int fileDifference, final int rankDifference) {
        if (fileDifference > 0) {
            if (rankDifference > 0) {
                return UP_RIGHT;
            }
            return DOWN_RIGHT;
        }
        if (rankDifference < 0) {
            return DOWN_LEFT;
        }
        return UP_LEFT;
    }
}
