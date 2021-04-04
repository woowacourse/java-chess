package chess.domain.piece.info;

public enum Color {
    WHITE("WHITE"),
    BLACK("BLACK"),
    NONE("NONE");

    private final String colorName;

    Color(String colorName) {
        this.colorName = colorName;
    }

    public boolean same(Color color) {
        return this == color;
    }

    public Color reverse() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
