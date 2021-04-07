package chess.controller.dto;

public class ScoreResponseDto {

    private final double whiteScore;
    private final double blackScore;

    public ScoreResponseDto(double whiteScore, double blackScore) {
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
