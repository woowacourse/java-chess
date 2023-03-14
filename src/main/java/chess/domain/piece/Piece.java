package chess.domain.piece;

public abstract class Piece {

    private final int rank;
    private final int file;
    private final Color color;

    protected Piece(final int rank, final int file, final Color color) {
        this.rank = rank;
        this.file = file;
        this.color = color;
    }
}
