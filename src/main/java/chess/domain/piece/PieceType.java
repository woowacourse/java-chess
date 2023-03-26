package chess.domain.piece;

public enum PieceType {

    ROOK(5),
    KNIGHT(2.5),
    BISHOP(3),
    QUEEN(9),
    KING(0),
    PAWN(1),
    EMPTY(0);


    private final double point;

    PieceType(final double point) {
        this.point = point;
    }

    public double getPoint() {
        return point;
    }
}
