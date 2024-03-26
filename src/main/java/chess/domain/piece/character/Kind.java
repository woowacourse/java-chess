package chess.domain.piece.character;

public enum Kind {
    PAWN(1),
    KNIGHT(2.5),
    BISHOP(3),
    ROOK(5),
    QUEEN(9),
    KING(0),
    ;

    private final double point;

    Kind(double point) {
        this.point = point;
    }

    public double point() {
        return point;
    }
}
