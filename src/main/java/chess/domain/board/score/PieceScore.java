package chess.domain.board.score;

public enum PieceScore {

    LOOK(5),
    BISHOP(3),
    KNIGHT(2.5),
    PAWN(0.5),
    QUEEN(9),
    KING(0);

    private final double value;

    PieceScore(final double value) {
        this.value = value;
    }

    public double value() {
        return value;
    }
}
