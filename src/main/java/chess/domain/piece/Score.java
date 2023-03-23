package chess.domain.piece;

public enum Score {
    QUEEN(9),
    ROOK(5),
    BISHOP(3),
    KNIGHT(2.5),
    PAWN(1),
    HALF_PAWN(0.5),
    KING(0),
    EMPTY(-1);

    private final double value;

    Score(final double value) {
        this.value = value;
    }

    public double value() {
        return value;
    }
}
