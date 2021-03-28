package chess.domain.piece;

public enum MaxDistance {
    KING(1),
    QUEEN(7),
    KNIGHT(1),
    ROOK(7),
    PAWN(2),
    BISHOP(7),
    EMPTY(0);

    private final int value;

    MaxDistance(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
