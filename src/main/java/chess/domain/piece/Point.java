package chess.domain.piece;

public enum Point {
    QUEEN(9),
    ROOK(5),
    BISHOP(3),
    KNIGHT(2.5),
    PAWN(1),
    KING(0),
    EMPTY(0);

    private final double point;

    Point(double point) {
        this.point = point;
    }

    public double valueOfPoint() {
        return point;
    }
}
