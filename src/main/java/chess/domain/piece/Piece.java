package chess.domain.piece;

public abstract class Piece {

    private final Color color;
    private final Position position;

    public Piece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }
}
