package domain.game;

public enum PieceType {
    PAWN(1),
    KING(0),
    BISHOP(3),
    KNIGHT(2.5),
    QUEEN(9),
    ROOK(5),
    EMPTY_PIECE(0);

    private final double score;

    PieceType(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
