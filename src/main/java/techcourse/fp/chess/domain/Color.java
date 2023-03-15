package techcourse.fp.chess.domain;

public enum Color {
    BLACK, WHITE;

    public boolean isSameColor(final Color color) {
        return this == color;
    }
}
