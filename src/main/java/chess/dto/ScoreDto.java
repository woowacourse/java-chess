package chess.dto;

import chess.domain.Score;

public class ScoreDto {

    private final double whiteScore;
    private final double blackScore;

    public ScoreDto(final Score whiteScore, final Score blackScore) {
        this.whiteScore = whiteScore.getValue();
        this.blackScore = blackScore.getValue();
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public double getBlackScore() {
        return blackScore;
    }
}
