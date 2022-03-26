package chess.model.piece;

public enum Point {
    KING(0), QUEEN(9), ROOK(5), BISHOP(3), KNIGHT(2.5), PAWN(1), EMPTY(0);

    private final double value;

    Point(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
