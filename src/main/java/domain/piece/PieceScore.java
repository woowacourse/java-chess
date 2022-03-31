package domain.piece;

public enum PieceScore {
    QUEEN(9),
    BISHOP(3),
    KNIGHT(2.5),
    ROOK(5),
    PAWN(1),
    KING(0);

    private static final double SEVERAL_PAWN_POINT = 0.5;

    private final double score;

    PieceScore(final double score) {
        this.score = score;
    }

    public double score(boolean isSeveralPawn) {
        if (this == PAWN && isSeveralPawn) {
            return SEVERAL_PAWN_POINT;
        }
        return this.score;
    }
}
