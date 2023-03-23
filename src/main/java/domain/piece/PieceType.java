package domain.piece;

import domain.chessboard.Type;

public enum PieceType implements Type {

    PAWN(1),
    BISHOP(3),
    KNIGHT(2.5),
    ROOK(5),
    QUEEN(9),
    KING(0);

    private final double score;

    PieceType(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

}
