package chess.domain;

public final class Position {

    private final char file;
    private final int rank;

    private Position(final char file, final int rank) {
        this.file = file;
        this.rank = rank;
    }

    public static Position of(final char file, final int rank) {
        return new Position(file, rank);
    }

    public char file() {
        return file;
    }

    public int rank() {
        return rank;
    }
}
