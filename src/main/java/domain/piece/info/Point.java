package domain.piece.info;

public enum Point {
    QUEEN(9.0),
    ROOK(5.0),
    BISHOP(3.0),
    KNIGHT(2.5),
    PAWN(1.0);

    private final double point;

    Point(final double point) {
        this.point = point;
    }
}
