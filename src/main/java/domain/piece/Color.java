package domain.piece;

public enum Color {
    WHITE,
    BLACK,
    NEUTRALITY;

    public boolean isBlack() {
        return this == BLACK;
    }

    boolean isNeutrality() {
        return this == NEUTRALITY;
    }

    Color opposite() {
        if (this == WHITE) {
            return BLACK;
        }
        if (this == BLACK) {
            return WHITE;
        }
        return NEUTRALITY;
    }
}
