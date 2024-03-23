package domain.piece;

public enum Color {

    WHITE, BLACK, NONE;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public boolean isOpposite(Color color) {
        return (isBlack() && color.isWhite()) || (isWhite() && color.isBlack());
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
