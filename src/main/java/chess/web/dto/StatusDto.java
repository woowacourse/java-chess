package chess.web.dto;

public class StatusDto {

    private final double whiteScore;
    private final double blackScore;

    public StatusDto(double whiteScore, double blackScore) {
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
