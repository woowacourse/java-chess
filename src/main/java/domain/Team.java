package domain;

public enum Team {
    WHITE, BLACK;

    public Team turn() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
