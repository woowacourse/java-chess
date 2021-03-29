package chess.domain.piece;

public enum Team {
    BLACK,
    WHITE;

    public boolean isBlack() {
        return this.equals(Team.BLACK);
    }

    public boolean isWhite() {
        return this.equals(Team.WHITE);
    }
}
