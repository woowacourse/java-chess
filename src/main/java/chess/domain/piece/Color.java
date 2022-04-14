package chess.domain.piece;

public enum Color {

    BLACK,
    WHITE;

    public boolean isSameColor(String color) {
        return this.name().equalsIgnoreCase(color);
    }
}
