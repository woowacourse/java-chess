package chess.domain.piece;

public enum Color {

    WHITE, BLACK, NO_COLOR;

    public static Color nextColorOf(Color previousColor) {
        if (previousColor == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
