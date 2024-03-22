package domain.piece.info;

public enum Color {
    BLACK,
    WHITE,
    NONE;

    public Color reverse() {
        if (this.equals(BLACK)) {
            return WHITE;
        }
        return BLACK;
    }

    public void isSameColor(final Color color) {
        if (this == color) {
            throw new IllegalArgumentException("같은 팀의 말이 있습니다.");
        }
    }
}
