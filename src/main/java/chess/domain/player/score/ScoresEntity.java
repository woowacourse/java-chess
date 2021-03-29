package chess.domain.player.score;

public class ScoresEntity {
    private final double whitePlayerScore;
    private final double blackPlayerScore;

    public ScoresEntity(double whitePlayerScore, double blackPlayerScore) {
        this.whitePlayerScore = whitePlayerScore;
        this.blackPlayerScore = blackPlayerScore;
    }

    public double getWhitePlayerScore() {
        return whitePlayerScore;
    }

    public double getBlackPlayerScore() {
        return blackPlayerScore;
    }
}
