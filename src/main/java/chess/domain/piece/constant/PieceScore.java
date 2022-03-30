package chess.domain.piece.constant;

public enum PieceScore {

    KING(0),
    QUEEN(9),
    BISHOP(3),
    ROOK(5),
    KNIGHT(2.5),
    PAWN(1),
    ;

    private final double score;

    PieceScore(final double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}