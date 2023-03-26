package chess.domain.piece;

public enum PieceType {
    EMPTY_PIECE(0),
    PAWN(0.5),
    ROOK(5),
    KNIGHT(2.5),
    BISHOP(3),
    QUEEN(9),
    KING(0);

    private final double score;

    PieceType(final double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }

}