package chess.Controller.dto;

import chess.domain.Status;

public class ScoreDto {

    private final double whiteScore;
    private final double blackScore;

    private ScoreDto(final Status status) {
        whiteScore = status.getWhiteScore();
        blackScore = status.getBlackScore();
    }

    public static ScoreDto fromEntity(final Status status) {
        return new ScoreDto(status);
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
