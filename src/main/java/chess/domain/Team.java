package chess.domain;

public enum Team {

    BLACK, WHITE;

    public boolean isBlack() {
        return this == BLACK;
    }

    public Team nextTurn() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
