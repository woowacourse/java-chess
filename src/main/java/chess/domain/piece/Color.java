package chess.domain.piece;

public enum Color {
    WHITE,
    BLACK,
    ;

    public boolean isSame(final String color) {
        return this.name().equals(color);
    }
}
