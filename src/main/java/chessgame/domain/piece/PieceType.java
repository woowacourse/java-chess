package chessgame.domain.piece;

public enum PieceType {

    KING,
    QUEEN,
    ROOK,
    BISHOP,
    KNIGHT,
    PAWN,
    EMPTY;

    public boolean isEmpty() {
        return this.equals(EMPTY);
    }
}
