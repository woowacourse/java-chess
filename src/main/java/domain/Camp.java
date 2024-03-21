package domain;

public enum Camp {
    WHITE, BLACK;

    public Camp toggle() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
