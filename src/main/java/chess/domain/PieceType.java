package chess.domain;

public enum PieceType {
    PAWN(1.0),
    ROOK(5.0),
    KNIGHT(1.5),
    BISHOP(3.0),
    QUEEN(9.0),
    KING(0.0),
    EMPTY(0.0);

    private final double score;

    PieceType(final double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
