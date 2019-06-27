package chess.application.dto;

public class ChessScoreDto {
    private double whiteScore;
    private double blackScore;

    public ChessScoreDto(double whiteScore, double blackScore) {
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
