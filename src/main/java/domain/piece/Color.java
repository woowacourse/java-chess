package domain.piece;

public enum Color {
    WHITE,
    BLACK,
    NEUTRALITY;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isNeutrality() {
        return this == NEUTRALITY;
    }
}
