package chess.piece;

public enum Team {
    BLACK, WHITE;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public Team getOppositeTeam() {
        if (this.isBlack()) {
            return WHITE;
        }
        return BLACK;
    }
}
