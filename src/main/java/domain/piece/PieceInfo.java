package domain.piece;

public enum PieceInfo {
    PAWN(1),
    ROOK(5),
    KNIGHT(2.5),
    BISHOP(3),
    QUEEN(9),
    KING(0),
    BLANK(0);

    private final double score;

    PieceInfo(double score) {
        this.score = score;
    }

    public boolean isNotBlank() {
        return this != BLANK;
    }

    public boolean isPawn() {
        return this == PAWN;
    }

    public double getScore() {
        return score;
    }
}
