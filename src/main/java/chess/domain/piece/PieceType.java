package chess.domain.piece;

public enum PieceType {
    QUEEN(new Score(9)),
    ROOK(new Score(5)),
    KNIGHT(new Score(2.5)),
    PAWN(new Score(1)),
    BISHOP(new Score(3)),
    KING(new Score(0));

    private final Score score;

    PieceType(final Score score) {
        this.score = score;
    }
}
