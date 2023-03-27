package chess.model.piece.score;

public enum PieceRuleScore {

    ZERO(new PieceScore(0.0d)),
    QUEEN(new PieceScore(9.0d)),
    ROOK(new PieceScore(5.0d)),
    BISHOP(new PieceScore(3.0d)),
    KNIGHT(new PieceScore(2.5d)),
    PAWN(new PieceScore(1.0d)),
    PAWN_OFFSET(new PieceScore(0.5d));

    private final PieceScore pieceScore;

    PieceRuleScore(final PieceScore pieceScore) {
        this.pieceScore = pieceScore;
    }

    public PieceScore score() {
        return pieceScore;
    }
}
