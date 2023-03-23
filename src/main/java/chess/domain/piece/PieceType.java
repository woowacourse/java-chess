package chess.domain.piece;

public enum PieceType {
    PAWN(1),
    ROOK(5),
    KNIGHT(2.5),
    BISHOP(3),
    QUEEN(9),
    EMPTY(0),
    KING(0);

    private final double point;

    PieceType(double point) {
        this.point = point;
    }

    public double getPoint() {
        return point;
    }
}
