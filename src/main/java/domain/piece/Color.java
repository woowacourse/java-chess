package domain.piece;

public enum Color {

    WHITE, BLACK, NONE;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() { // TODO: 사용
        return this == WHITE;
    }

    public boolean isDifferent(Color color) {
        return this != color;
    }

    public Color oppositeColor() {
        if (isBlack()) {
            return WHITE;
        }
        if (isWhite()) {
            return BLACK;
        }
        return NONE;
    }
}
