package chess.domain.piece;

public enum PieceType {

    BISHOP(3),
    KING(0),
    KNIGHT(2.5),
    QUEEN(9),
    ROOK(5),
    PAWN(1),
    EMPTY(0);

    private final double point;

    PieceType(double point) {
        this.point = point;
    }

    public double getPoint() {
        return point;
    }
}
