package domain.piece;

public enum Color {

    WHITE, BLACK, NONE;

    public boolean isBlack() {
        return this == BLACK;
    }
}
