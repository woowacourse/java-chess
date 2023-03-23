package chess.domain.piece;

public enum PieceSymbol {
    PAWN(1),
    BISHOP(3),
    KING(0),
    KNIGHT(2.5),
    QUEEN(9),
    ROOK(5),
    EMPTY(0),
    ;

    private final double score;

    PieceSymbol(final double score) {
        this.score = score;
    }

    public double getPieceScore() {
        return score;
    }
}