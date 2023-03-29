package dto;

public class ChessGameScoreDto {

    private final double blackScore;
    private final double whiteScore;

    public ChessGameScoreDto(double blackScore, double whiteScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
