package chess.db.domain.game;

public class ScoresEntity {
    private final double blackPlayerScore;
    private final double whitePlayerScore;

    public ScoresEntity(double blackPlayerScore, double whitePlayerScore) {
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
