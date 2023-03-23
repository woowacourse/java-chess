package domain.piece;

public enum PieceType {
    PAWN,
    ROOK,
    KNIGHT,
    BISHOP,
    QUEEN,
    KING,
    BLANK;

    public boolean isNotBlank() {
        return this != BLANK;
    }

    public boolean isPawn() {
        return this == PAWN;
    }
}
