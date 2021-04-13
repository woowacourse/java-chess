package chess.domain.piece;

public enum Color {
    WHITE("white"), BLACK("black"), NOTHING("");

    private final String name;

    Color(String name) {
        this.name = name;
    }

    public static Color parseColor(String current_color) {
        if (current_color.equals("white")) {
            return WHITE;
        }
        return BLACK;
    }

    public boolean isSameAs(Color color) {
        return this == color;
    }

    public String getName() {
        return this.name;
    }
}
