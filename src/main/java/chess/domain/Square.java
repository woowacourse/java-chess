package chess.domain;

public class Square {
    private final Rank rank;
    private final File file;

    public Square(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }
}
