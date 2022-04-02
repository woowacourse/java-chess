package chess.model;

public class GameScore {

    private final double whiteScore;
    private final double blackScore;

    public GameScore(double whiteScore, double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public double whiteScore() {
        return whiteScore;
    }

    public double blackScore() {
        return blackScore;
    }
}
