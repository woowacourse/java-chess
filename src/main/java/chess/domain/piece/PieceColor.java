package chess.domain.piece;

public enum PieceColor {
    BLACK,
    WHITE,
    VOID;

    public PieceColor oppositeColor() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
