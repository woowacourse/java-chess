package chess.domain.piece;

public enum PieceType {
    QUEEN(Score.create(9)),
    ROOK(Score.create(5)),
    KNIGHT(Score.create(2.5)),
    PAWN(Score.create(1)),
    BISHOP(Score.create(3)),
    KING(Score.create(0));

    private final Score score;

    PieceType(final Score score) {
        this.score = score;
    }

    public Score getScore() {
        return score;
    }
}
