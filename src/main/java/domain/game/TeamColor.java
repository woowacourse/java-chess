package domain.game;

public enum TeamColor {
    BLACK, WHITE;

    public TeamColor toggle() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
