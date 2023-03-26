package chess.controller;

import chess.domain.piece.TeamColor;
import chess.domain.result.Score;

public class ScoreDto {
    private final Double whiteScore;
    private final Double blackScore;

    public ScoreDto(Score score) {
        whiteScore = score.getScore(TeamColor.WHITE);
        blackScore = score.getScore(TeamColor.BLACK);
    }

    public Double getWhiteScore() {
        return whiteScore;
    }

    public Double getBlackScore() {
        return blackScore;
    }
}
