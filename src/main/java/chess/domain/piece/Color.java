package chess.domain.piece;

public enum Color {
    WHITE,
    BLACK,
    EMPTY,
    ;

    public boolean isOpponent(final Color color) {
        if (color == EMPTY) {
            return false;
        }
        return this != color;
    }
}
