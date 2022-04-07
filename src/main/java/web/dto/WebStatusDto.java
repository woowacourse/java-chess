package web.dto;

public class WebStatusDto {

    private final double whiteScore;
    private final double blackScore;

    public WebStatusDto(double whiteScore, double blackScore) {
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
