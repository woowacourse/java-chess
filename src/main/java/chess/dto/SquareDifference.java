package chess.domain.square;

public final class SquareDifference {

    private final int fileDifference;
    private final int rankDifference;

    private SquareDifference(final int fileDifference, final int rankDifference) {
        this.fileDifference = fileDifference;
        this.rankDifference = rankDifference;
    }

    public static SquareDifference of(final Square current, final Square destination) {
        final int fileDifference = current.getFileDifference(destination);
        final int rankDifference = current.getRankDifference(destination);
        return new SquareDifference(fileDifference, rankDifference);
    }

    public int getFileDifference() {
        return fileDifference;
    }

    public int getRankDifference() {
        return rankDifference;
    }
}
