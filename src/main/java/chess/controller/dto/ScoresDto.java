package chess.controller.dto;

public class ScoresDto {
    private final double blackScore;
    private final double whiteScore;

    public ScoresDto(final double blackScore, final double whiteScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
