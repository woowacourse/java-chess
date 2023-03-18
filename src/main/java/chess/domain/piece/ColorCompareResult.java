package chess.domain.piece;

public enum ColorCompareResult {
    SAME_COLOR,
    DIFFERENT_COLOR,
    EMPTY;

    public static ColorCompareResult of(Color color1, Color color2) {
        if (color1 == Color.NONE || color2 == Color.NONE) {
            return EMPTY;
        }
        if (color1 == color2) {
            return SAME_COLOR;
        }
        return DIFFERENT_COLOR;
    }
}
