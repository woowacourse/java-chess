package domain;

public class Piece {
    private final PieceType pieceType;

    public Piece(final PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
