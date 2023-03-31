package chess.domain.piece;

public enum RoleType {
    BISHOP(3),
    KING(0),
    KNIGHT(2.5),
    PAWN(1),
    QUEEN(9),
    ROOK(5),
    EMPTY(0),
    ;

    private final double score;

    RoleType(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
