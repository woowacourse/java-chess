package chess;

public enum Color {

    BLACK("검"),
    WHITE("백"),
    EMPTY("X");

    private final String title;

    Color(final String title) {
        this.title = title;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public Color opposite() {
        return switch (this) {
            case BLACK -> WHITE;
            case WHITE -> BLACK;
            default -> EMPTY;
        };
    }

    @Override
    public String toString() {
        return title;
    }
}
