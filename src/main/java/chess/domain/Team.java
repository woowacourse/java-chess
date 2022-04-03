package chess.domain;

public enum Team {
    BLACK,
    WHITE;

    public boolean isSame(Team other) {
        return this.equals(other);
    }
}
