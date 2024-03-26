package chess.domain.piece;

public enum Color {
    BLACK,
    WHITE;

    Color() {
    }

    public static Color toggleColor(Color color) {
        if (color == BLACK) {
            return WHITE;
        }
        return BLACK;
    }
}
