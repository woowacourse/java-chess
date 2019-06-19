package chess.domain;

public enum Team {
    BLACK, WHITE;

    public Team turnChanged() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
