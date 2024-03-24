package domain.piece;

public enum Color {

    WHITE,
    BLACK,
    EMPTY;

    public boolean isBlack() {
        return this == BLACK;
    }
}
