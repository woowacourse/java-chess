package chess.domain.piece;

public enum Color {
    WHITE("White"),
    BLACK("Black");

    private final String label;

    Color(final String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    public static Color ofString(String input) {
        return Color.valueOf(input.toUpperCase());
    }
}
