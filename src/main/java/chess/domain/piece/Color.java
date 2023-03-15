package chess.domain.piece;

public enum Color {
    WHITE,
    BLACK,
    ;

    public boolean isOpponent(final Color color) {
        return this != color;
    }
}
