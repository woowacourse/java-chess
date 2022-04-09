package chess.Controller.dto;

import chess.domain.Score;

public class ScoreDto {

    private final double whiteScore;
    private final double blackScore;

    private ScoreDto(final Score status) {
        whiteScore = status.getWhiteScore();
        blackScore = status.getBlackScore();
    }

    public static ScoreDto fromEntity(final Score status) {
        return new ScoreDto(status);
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
