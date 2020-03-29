package chess.team;

public enum Team {
    BLACK,
    WHITE;

    public boolean isBlack() {
        return this == BLACK;
    }

    public Team changeTurn() {
        if (this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
