package domain.piece;

public enum Color {

    BLACK,
    WHITE;

    public Color changeColor() {
        if(this == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
