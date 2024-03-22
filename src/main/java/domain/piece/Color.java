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

    public boolean isOppositeColor(Color other) {
        if (this == WHITE) {
            return other == BLACK;
        }
        if (this == BLACK) {
            return other == WHITE;
        }
        return false;
    }
}
