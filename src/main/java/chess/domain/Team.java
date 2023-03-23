package chess.domain;

public enum Team {
    BLACK,
    WHITE,
    NEUTRALITY;

    public boolean isNeutrality() {
        return this.equals(NEUTRALITY);
    }

    public boolean isWhite() {
        return this.equals(WHITE);
    }

    public boolean isAlly(final Team team) {
        return this.equals(team);
    }
}
