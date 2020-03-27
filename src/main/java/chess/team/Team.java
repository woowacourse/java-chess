package chess.team;

public enum Team {
    BLACK(true),
    WHITE(false);

    private final boolean isBlack;

    Team(boolean isBlack) {
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public Team changeTurn() {
        if(this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
