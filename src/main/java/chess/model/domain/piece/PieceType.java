package chess.model.domain.piece;

public enum PieceType {
    EMPTY,
    PAWN,
    KNIGHT,
    BISHOP,
    ROOK,
    QUEEN,
    KING;

    public boolean isPawn() {
        return PAWN == this;
    }
}
