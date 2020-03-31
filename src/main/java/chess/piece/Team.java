package chess.piece;

public enum Team {
    BLACK, WHITE;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isNotSame(Team team) {
        return this != team;
    }

    public Team getOppositeTeam() {
        return isWhite() ? BLACK : WHITE;
    }
}
