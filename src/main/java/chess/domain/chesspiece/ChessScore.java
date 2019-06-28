package chess.domain.chesspiece;

public enum ChessScore {
    BISHOP(3),
    KING(0),
    KNIGHT(2.5),
    PAWN(1),
    QUEEN(9),
    ROOK(5),
    BLANK(0);

    private double score;

    ChessScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
