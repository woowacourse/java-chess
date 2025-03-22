package chess;

public enum Color {

    BLACK("흑"),
    WHITE("백"),
    EMPTY("없음");

    Color(String name) {
        this.name = name;
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

    private final String name;

    public String getName() {
        return name;
    }
}
