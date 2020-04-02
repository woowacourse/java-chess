package chess.domain;

public enum Team {
    WHITE,
    BLACK;

    public Team opponent() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
