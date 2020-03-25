package chess.piece;

public class Piece {
    private final PieceColor pieceColor;
    private final PieceType pieceType;

    public Piece(PieceColor pieceColor, PieceType pieceType) {
        this.pieceColor = pieceColor;
        this.pieceType = pieceType;
    }
}
