package chess.domain;

public enum WinResult {

    BLACK,
    WHITE,
    DRAW;

    public static WinResult of(double blackScore, double whiteScore) {
        if (blackScore > whiteScore) {
            return BLACK;
        }
        if (blackScore < whiteScore) {
            return WHITE;
        }
        return DRAW;
    }
}
