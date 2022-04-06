package chess.dto;

public class ScoreDto {
    private final double whiteScore;
    private final double blackScore;

    public ScoreDto(final double whiteScore, final double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }
}
