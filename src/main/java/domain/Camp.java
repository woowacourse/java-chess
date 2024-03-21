package domain;

public enum Camp {
    WHITE, BLACK;

    public Camp turnAlternation() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
