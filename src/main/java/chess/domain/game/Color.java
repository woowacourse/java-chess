package chess.domain.game;

public enum Color {
    WHITE,
    BLACK;

    public boolean isBlack() {
        return this == BLACK;
    }

    public String convertByColor(String name) {
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
