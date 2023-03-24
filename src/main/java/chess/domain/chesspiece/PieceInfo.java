package chess.domain.chesspiece;

public enum PieceInfo {
    EMPTY_PIECE(0),
    PAWN(1),
    ROOK(5),
    BISHOP(3),
    KNIGHT(2.5),
    QUEEN(9),
    KING(0);

    private final double score;

    PieceInfo(final double score) {
        this.score = score;
    }

    public double addScore(final double score) {
        return score + this.score;
    }
}
