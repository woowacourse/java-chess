package chess.domain.piece;

public final class Piece {
    private final PieceType pieceType;

    public Piece(final PieceType pieceType) {
        this.pieceType = pieceType;
    }

    @Override
    public String toString() {
        return "Piece{" +
                "pieceType=" + pieceType +
                '}';
    }
}
