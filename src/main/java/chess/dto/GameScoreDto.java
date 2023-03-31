package chess.dto;

public class GameScoreDto {

    private final double whiteScore;
    private final double blackScore;

    private GameScoreDto(final double whiteScore, final double blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public static GameScoreDto of(final double whiteScore, final double blackScore) {
        return new GameScoreDto(whiteScore, blackScore);
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }

}
