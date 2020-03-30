package chess.domain;

public enum Color {
    BLACK("BLACK"),
    WHITE("WHITE");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public Color changeColor(Color color) {
        if (color.equals(BLACK)) {
            return WHITE;
        }
        return BLACK;
    }

    public String getName() {
        return name;
    }
}
