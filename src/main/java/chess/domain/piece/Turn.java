package chess.domain.piece;

public enum Turn {
    WHITE,
    BLACK,
    BLANK;

    public boolean isNotSame(final Turn turn) {
        return this != turn && this != BLANK && turn != BLANK;
    }
}
