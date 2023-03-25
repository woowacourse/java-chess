package chess.domain.piece;

public enum MovementType {

    KING(0),
    QUEEN(9),
    ROOK(5),
    BISHOP(3),
    KNIGHT(2.5),
    BLACK_PAWN(1),
    WHITE_PAWN(1),
    ;

    private final double value;

    MovementType(final double value) {
        this.value = value;
    }

    public double value() {
        return value;
    }
}
