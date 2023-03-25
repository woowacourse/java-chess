package chessgame.domain.piecetype;

public enum PieceTypeSymbol {

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
