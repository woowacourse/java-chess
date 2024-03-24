package domain.game;

public enum PieceType {
    BLACK_PAWN(1), BLACK_KNIGHT(2.5), BLACK_BISHOP(3), BLACK_ROOK(5), BLACK_QUEEN(9), BLACK_KING(0),
    WHITE_PAWN(1), WHITE_KNIGHT(2.5), WHITE_BISHOP(3), WHITE_ROOK(5), WHITE_QUEEN(9), WHITE_KING(0);

    private final double value;

    PieceType(double value) {
        this.value = value;
    }

    public double value() {
        return value;
    }
}
