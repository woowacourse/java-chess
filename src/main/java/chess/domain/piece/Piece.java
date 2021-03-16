package chess.domain.piece;

public class Piece {

    private PieceKind pieceKind;
    private PieceColor pieceColor;

    public Piece(PieceKind pieceKind, PieceColor pieceColor) {
        this.pieceKind = pieceKind;
        this.pieceColor = pieceColor;
    }

    public boolean isSameColor(Piece piece) {
        return this.pieceColor == piece.getPieceColor();
    }

    public String getSymbol() {
        return pieceKind.getName(pieceColor);
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }
}
