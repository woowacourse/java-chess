package chess.dto;

public class ScoreDto {
    private final double blackScore;
    private final double whiteScore;

    private ScoreDto(final double blackScore, final double whiteScore) {
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public static ScoreDto of(final double blackScore, final double whiteScore) {
        return new ScoreDto(blackScore, whiteScore);
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

}
