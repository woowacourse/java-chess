package chess.domain;

public abstract class Piece {

    protected final Position position;
    protected final Color color;

    protected Piece(final File file, final Rank rank, final Color color) {
        this.color = color;
        this.position = new Position(file, rank);
    }
}
