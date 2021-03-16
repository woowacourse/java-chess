package chess.domain.position;

public class Position {
    private final Rank rank;
    private final File file;

    public Position(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }
}
