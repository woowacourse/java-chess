package domain.chessboard;

public class StatusResult {

    private final Score score;
    private final Result result;

    private StatusResult(Score score, Result result) {
        this.score = score;
        this.result = result;
    }

    public static StatusResult of(ColorScore blackScore, ColorScore whiteScore) {
        Score score = Score.of(blackScore, whiteScore);
        Result result = Result.createByScore(score);

        return new StatusResult(score, result);
    }

    public Score getScore() {
        return score;
    }

    public Result getResult() {
        return result;
    }

}
