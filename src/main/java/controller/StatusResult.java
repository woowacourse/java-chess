package controller;

import domain.chessboard.Score;

public class StatusResult {

    private final Score score;
    private final Result result;

    private StatusResult(Score score, Result result) {
        this.score = score;
        this.result = result;
    }

    public static StatusResult of(Score score) {
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
