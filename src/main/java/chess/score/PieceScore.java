package chess.score;

public enum PieceScore {

    PAWN(1),
    KNIGHT(2.5),
    BISHOP(3),
    ROOK(5),
    QUEEN(9),
    KING(0),
    ;

    private final double score;

    PieceScore(double score) {
        this.score = score;
    }

    public Score asScore() {
        return Score.of(score);
    }
}
