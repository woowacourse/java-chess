package chess.domain.piece;

public enum PieceType {

    PAWN(1.0),
    ROOK(5.0),
    KNIGHT(2.5),
    BISHOP(3.0),
    QUEEN(9.0),
    KING(0.0);

    private static final double PAWN_SPECIAL_VALUE = 0.5;

    private final double value;

    PieceType(final double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public static double getPawnSpecialValue() {
        return PAWN_SPECIAL_VALUE;
    }
}
