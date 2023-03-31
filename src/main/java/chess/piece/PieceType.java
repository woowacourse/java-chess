package chess.piece;

public enum PieceType {
    PAWN(1),
    ROOK(5),
    KNIGHT(2.5),
    BISHOP(3),
    QUEEN(9),
    KING(0),
    EMPTY(0),
    ;

    private final double score;

    PieceType(final double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
