package chess.dto;

public class ScoreDto {
    private double whiteScore;
    private double blackScore;

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
