package chess.domain.player;

public class Scores {
    private final double blackPlayerScore;
    private final double whitePlayerScore;

    public Scores(double blackPlayerScore, double whitePlayerScore) {
        this.blackPlayerScore = blackPlayerScore;
        this.whitePlayerScore = whitePlayerScore;
    }

    public double getBlackPlayerScore() {
        return blackPlayerScore;
    }

    public double getWhitePlayerScore() {
        return whitePlayerScore;
    }
}
