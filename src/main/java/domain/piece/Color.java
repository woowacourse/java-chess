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

    public boolean isOpposite(final Color color) {
        return this != color && !color.isNeutrality();
    }
}
