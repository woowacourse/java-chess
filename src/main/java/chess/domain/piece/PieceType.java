package chess.domain.piece;

public enum PieceType {
    WHITE_PAWN(1),
    BLACK_PAWN(1),
    KNIGHT(2.5),
    BISHOP(3),
    ROOK(5),
    QUEEN( 9),
    KING(0),
    BLANK(0);

    private final double score;

    PieceType(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}
