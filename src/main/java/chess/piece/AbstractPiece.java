package chess.piece;

public abstract class AbstractPiece implements Piece {

    private final Color color;

    AbstractPiece(final Color color) {
        this.color = color;
    }
}
