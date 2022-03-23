package chess;

public class Piece {
    private final PieceType pieceType;
    private final PieceColor pieceColor;

    public Piece(PieceType pieceType, PieceColor pieceColor) {
        this.pieceType = pieceType;
        this.pieceColor = pieceColor;
    }

    public boolean isSameType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    public String getEmblem() {
        return pieceType.getName(pieceColor);
    }
}
