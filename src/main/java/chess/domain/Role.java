package chess.domain;

public enum Role {
    PAWN(8),
    ROOK(2),
    KNIGHT(2),
    BISHOP(2),
    QUEEN(1),
    KING(1);

    private final int count;

    Role(final int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
