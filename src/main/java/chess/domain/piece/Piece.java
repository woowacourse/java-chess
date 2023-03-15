package chess.domain.piece;

public abstract class Piece {
    private final Color color;
    private final PieceType pieceType;

    protected Piece(final Color color, final PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }
}
