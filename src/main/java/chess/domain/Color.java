package chess.domain;

public enum Color {

    WHITE("White"),
    BLACK("Black"),
    ;

    private final String name;

    Color(final String name) {
        this.name = name;
    }

    public Color reverse() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }

    public String getName() {
        return name;
    }
}
