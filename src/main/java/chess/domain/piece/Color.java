package chess.domain.piece;

public enum Color {
    BLACK("black"),
    WHITE("white"),
    NONE("none");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public Color switchColor() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }

    public static Color getColor(String name) {
        if (BLACK.name.equals(name)) {
            return BLACK;
        }
        if (WHITE.name.equals(name)) {
            return WHITE;
        }
        return NONE;
    }

    public String getName() {
        return name;
    }
}
