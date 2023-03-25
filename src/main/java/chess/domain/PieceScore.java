package chess.domain;

public enum PieceScore {

    KING(0),
    QUEEN(9),
    ROOK(5),
    BISHOP(3),
    KNIGHT(2.5),
    PAWN_WITHOUT_SAME_FILE(1),
    PAWN_WITH_SAME_FILE(0.5);

    private final double score;

    PieceScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
