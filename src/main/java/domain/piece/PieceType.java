package domain.piece;

import domain.chessboard.Type;

public enum PieceType implements Type {

    PAWN(1),
    INIT_PAWN(1),
    BISHOP(3),
    KNIGHT(2.5),
    ROOK(5),
    QUEEN(9),
    KING(0);

    private final double value;

    PieceType(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

}
