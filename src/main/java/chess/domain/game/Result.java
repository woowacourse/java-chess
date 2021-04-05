package chess.domain.game;

public class Result {
    private final double blackScore;
    private final double whiteScore;

    public Result(double blackScore, double whiteScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public String getBlackOutcome() {
        return Outcome.outcome(blackScore, whiteScore);
    }

    public String getWhiteOutcome() {
        return Outcome.outcome(whiteScore, blackScore);
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
