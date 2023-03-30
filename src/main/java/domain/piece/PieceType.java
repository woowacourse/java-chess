package domain.piece;

public enum PieceType {
    PAWN(1.0),
    KNIGHT(2.5),
    BISHOP(3.0),
    ROOK(5.0),
    QUEEN(9.0),
    KING(0.0);

    private final double value;

    PieceType(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

}
