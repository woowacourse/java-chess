package chess.domain;

public enum Result {

    WHITE_WIN_CAPTURE_KING("화이트가 킹을 잡아 승리했습니다!"),
    WHITE_WIN_SCORE("화이트가 더 높은 점수로 승리했습니다!"),
    DRAW("무승부입니다!"),
    BLACK_WIN_CAPTURE_KING("블랙이 킹을 잡아 승리했습니다!"),
    BLACK_WIN_SCORE("블랙이 더 높은 점수로 승리했습니다!");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public static Result of(double whiteScore, double blackScore) {
        if (whiteScore > blackScore) {
            return Result.WHITE_WIN_SCORE;
        }
        if (whiteScore < blackScore) {
            return Result.BLACK_WIN_SCORE;
        }
        return Result.DRAW;
    }

    public String getResult() {
        return result;
    }
}
