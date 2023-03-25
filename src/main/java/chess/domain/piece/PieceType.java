package chess.domain.piece;

public enum PieceType {
    BISHOP(3),
    ROOK(5),
    QUEEN(9),
    KNIGHT(2.5),
    KING(0),
    PAWN(1),
    BLANK(0);

    private final double score;

    PieceType(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
