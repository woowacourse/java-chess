package domain.piece;

public enum Color {
    BLACK,
    WHITE;

    public static Color reverseColor(Color color) {
        if (color == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
