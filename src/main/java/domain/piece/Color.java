package domain.piece;

public enum Color {
    WHITE,
    BLACK,
    NEUTRALITY;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isNeutrality() {
        return this == NEUTRALITY;
    }

    public boolean isSameColor(Color other) {
        return this == other;
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
