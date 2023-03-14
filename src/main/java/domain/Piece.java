package domain;

public class Piece {

    private final Type type;
    private final Color color;

    private Piece(final Type type, final Color color) {
        this.type = type;
        this.color = color;
    }

    public static Piece of(final Type type, final Color color) {
        return new Piece(type, color);
    }
}
