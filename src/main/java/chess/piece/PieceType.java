package chess.piece;

public enum PieceType {

    BISHOP(true),
    KING(false),
    KNIGHT(false),
    PAWN(false),
    QUEEN(true),
    ROOK(true);

    private final boolean canBeBlocked;

    PieceType(boolean canBeBlocked) {
        this.canBeBlocked = canBeBlocked;
    }

    public boolean canBeBlocked() {
        return canBeBlocked;
    }
}
