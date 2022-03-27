package chess.domain.board;

public enum Result {
    WHITE_WIN,
    BLACK_WIN,
    DRAW
    ;

    public static Result calculateResult(double whiteScore, double blackScore) {
        if (whiteScore > blackScore) {
            return Result.WHITE_WIN;
        }
        if (whiteScore < blackScore) {
            return Result.BLACK_WIN;
        }
        return Result.DRAW;
    }
}
