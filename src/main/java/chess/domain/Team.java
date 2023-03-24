package chess.domain;

public enum Team {
    BLACK,
    WHITE,
    NEUTRALITY;

    public boolean isNeutrality() {
        return this.equals(NEUTRALITY);
    }
}
