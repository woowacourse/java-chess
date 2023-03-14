package chess.domain;

public abstract class Piece {

    private final int rank;
    private final int file;

    protected Piece(final int rank, final int file) {
        this.rank = rank;
        this.file = file;
    }
}
