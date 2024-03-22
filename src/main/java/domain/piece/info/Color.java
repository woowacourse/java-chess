package domain.piece.info;

import domain.piece.Piece;

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

    public static void isSame(final Piece source, final Piece target) {
        if (target.color().equals(source.color())) {
            throw new IllegalArgumentException("같은 팀의 말이 있습니다.");
        }
    }
}
