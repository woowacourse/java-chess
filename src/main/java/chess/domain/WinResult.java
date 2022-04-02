package chess.domain;

public enum WinResult {

    BLACK,
    WHITE,
    DRAW;

    public static WinResult of(Score blackScore, Score whiteScore) {
        if (blackScore.isOverThan(whiteScore)) {
            return BLACK;
        }
        if (whiteScore.isOverThan(blackScore)) {
            return WHITE;
        }
        return DRAW;
    }
}
