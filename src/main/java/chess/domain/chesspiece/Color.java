package chess.domain.chesspiece;

public enum Color {

    WHITE,
    BLACK;

    public boolean isBlack() {
        return this.equals(BLACK);
    }

    public String convertByColor(final String name) {
        if (isBlack()) {
            return name.toUpperCase();
        }
        return name.toLowerCase();
    }

    public Color toOpposite() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
