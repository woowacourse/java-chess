package chess.domain.chesspiece;

public enum Color {

    WHITE("white"),
    BLACK("black");

    private final String value;

    Color(final String value) {
        this.value = value;
    }

    public boolean isBlack() {
        return this.equals(BLACK);
    }

    public Color toOpposite() {
        if (isBlack()) {
            return WHITE;
        }
        return BLACK;
    }

    public String getValue() {
        return value;
    }
}
