package chess.domain.piece;

public abstract class AbstractPiece {

    private final PieceColor pieceColor;

    public AbstractPiece(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }
}
