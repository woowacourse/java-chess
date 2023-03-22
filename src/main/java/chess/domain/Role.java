package chess.domain;

public enum Role {

    KING(0),
    QUEEN(9),
    ROOK(5),
    BISHOP(3),
    KNIGHT(2.5),
    PAWN(1),
    EMPTY(0);

    private final double score;

    Role(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
