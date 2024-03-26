package domain.piece.attribute;

public enum Color {
    BLACK,
    WHITE;

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
