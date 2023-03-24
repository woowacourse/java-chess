package techcourse.fp.chess.domain.piece;

public enum PieceType {
    PAWN(1),
    KNIGHT(2.5),
    BISHOP(3),
    ROOK(5),
    QUEEN(9),
    KING(0),
    EMPTY(0);

    private final double score;

    PieceType(final double score) {
        this.score = score;
    }


    public double getScore() {
        return score;
    }
}
