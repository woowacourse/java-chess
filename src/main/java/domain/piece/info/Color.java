package domain.piece.info;

public enum Color {
    BLACK,
    WHITE,
    NONE;

    public static Color opposite(final Color color) {
        if (color.equals(BLACK)) {
            return WHITE;
        }
        return BLACK;
    }
}
