package chess.domain.pieces.piece;

public enum Type {
    KING(0),
    QUEEN(9),
    ROOK(5),
    BISHOP(3),
    KNIGHT(2.5),
    PAWN(1),
    ;

    private final double score;

    Type(final double score) {
        this.score = score;
    }

    public Score getScore() {
        return Score.of(score);
    }
}
