package chess.dto;

public class ResultDto {
    private double whiteScore;
    private double blackScore;

    public double getWhiteScore() {
        return whiteScore;
    }

    public void setWhiteScore(final double whiteScore) {
        this.whiteScore = whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public void setBlackScore(final double blackScore) {
        this.blackScore = blackScore;
    }
}
