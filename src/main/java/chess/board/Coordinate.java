package chess.board;

public final class Coordinate {
    private final File file;
    private final Rank rank;

    public Coordinate(final File file, final Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    public Variation calculateVariation(final Coordinate coordinate) {
        return new Variation(coordinate.file.subtract(this.file), coordinate.rank.subtract(this.rank));
    }
}
