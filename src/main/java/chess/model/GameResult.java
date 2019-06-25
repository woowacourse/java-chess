package chess.model;

public class GameResult {
    private GameFlow result;
    private double whiteScore;
    private double blackScore;

    public GameResult(final GameFlow result, final double whiteScore, final double blackScore) {
        this.result = result;
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public GameFlow getResult() {
        return result;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
