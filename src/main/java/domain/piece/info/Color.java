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

    public void isSameColor(final Color color) {
        if (this.equals(color)) {
            throw new IllegalArgumentException("같은 팀의 말이 있습니다.");
        }
    }
}
