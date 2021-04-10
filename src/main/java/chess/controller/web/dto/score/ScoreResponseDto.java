package chess.controller.web.dto.score;

public class ScoreResponseDto {

    private final double whiteScore;
    private final double blackScore;

    public ScoreResponseDto(final double whiteScore, final double blackScore) {
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
