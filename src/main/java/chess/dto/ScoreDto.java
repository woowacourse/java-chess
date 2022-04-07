package chess.dto;

public class ScoreDto {
    private final double whiteScore;
    private final double blackScore;

    public ScoreDto(double whiteScore, double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
