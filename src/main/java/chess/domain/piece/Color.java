package chess.domain.piece;

public enum Color {
    BLACK("Black"),
    WHITE("White");

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
