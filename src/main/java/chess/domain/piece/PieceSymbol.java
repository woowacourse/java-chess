package chess.domain.piece;

public enum PieceSymbol {
    PAWN(0.5),
    BISHOP(3),
    KING(0),
    KNIGHT(2.5),
    QUEEN(9),
    ROOK(5),
    EMPTY(0),
    ;

    private final double pieceValue;

    PieceSymbol(final double pieceValue) {
        this.pieceValue = pieceValue;
    }

    public double getPieceValue() {
        return pieceValue;
    }
}