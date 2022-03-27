package chess.domain;

public class Position {
    private File file;
    private Rank rank;

    public Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }
}
