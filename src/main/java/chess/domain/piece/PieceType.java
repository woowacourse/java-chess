package chess.domain.piece;

public enum PieceType {
    ROOK(5),
    KNIGHT(2.5),
    BISHOP(3),
    QUEEN(9),
    KING(0),
    PAWN(1),
    EMPTY(0);

    private static final int INITIAL_STATE = 0;

    private final double score;

    PieceType(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
