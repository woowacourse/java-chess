package chess.domain.result;

public class ScoreResult implements Result {

    private final double blackScore;
    private final double whiteScore;

    public ScoreResult(double blackScore, double whiteScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
