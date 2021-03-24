package chess.domain.result;

public class Result {
    private static final Result EMPTY_RESULT = new Result(new Score(0), new Score(0));

    private final Score blackTeamScore;
    private final Score whiteTeamScore;

    private Result(Score blackTeamScore, Score whiteTeamScore) {
        this.blackTeamScore = blackTeamScore;
        this.whiteTeamScore = whiteTeamScore;
    }

    public static Result generateResult(double blackTeamScore, double whiteTeamScore) {
        return new Result(new Score(blackTeamScore), new Score(whiteTeamScore));
    }

    public static Result getEmptyResult() {
        return EMPTY_RESULT;
    }

    public double getBlackTeamScore() {
        return blackTeamScore.getScore();
    }

    public double getWhiteTeamScore() {
        return whiteTeamScore.getScore();
    }
}
