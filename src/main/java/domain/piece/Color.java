package domain.piece;

public enum Color {
    BLACK,
    WHITE;

    public int moveUnit() {
        if (this == BLACK) {
            return -1;
        }
        return 1;
    }
}
