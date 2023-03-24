package chess.domain.piece;

public enum Role {
    PAWN(1),
    ROOK(5),
    KNIGHT(2.5),
    BISHOP(3),
    QUEEN(9),
    KING(0),
    BLANK(0),
    ;

    private final double score;

    Role(final double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
