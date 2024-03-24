package chess.domain.piece;

public enum Team {

    BLACK, WHITE;

    public boolean isBlack() {
        return this == BLACK;
    }

    public Team next() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
