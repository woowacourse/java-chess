package chess.domain.piece;

public enum Color {
    WHITE("WHITE"),
    BLACK("BLACK"),
    NOTHING("NOTHING");

    Color(String colorName) {
    }

    public boolean isSameAs(Color color) {
        return this.equals(color);
    }
}
