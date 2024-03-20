package chess.model;

public class ChessPosition {
    private final File file;
    private final Rank rank;

    public ChessPosition(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Distance calculateDistance(ChessPosition other) {
        int fileDifference = file.minus(other.file);
        int rankDifference = rank.minus(other.rank);
        return new Distance(fileDifference, rankDifference);
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }
}
