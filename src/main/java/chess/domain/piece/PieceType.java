package chess.domain.piece;

public enum PieceType {
    KING(0),
    QUEEN(9),
    ROOK(5),
    BISHOP(3),
    KNIGHT(2.5),
    PAWN(1),
    ;

    private final double defaultScore;

    PieceType(final double defaultScore) {
        this.defaultScore = defaultScore;
    }

    public double defaultScore() {
        return defaultScore;
    }
}
