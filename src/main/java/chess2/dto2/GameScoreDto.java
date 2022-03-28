package chess2.dto2;

public class GameScoreDto {

    private final double whiteScore;
    private final double blackScore;

    public GameScoreDto(double whiteScore, double blackScore) {
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
