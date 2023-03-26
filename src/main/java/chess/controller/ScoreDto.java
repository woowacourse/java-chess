package chess.controller;

import chess.domain.Score;
import chess.domain.piece.TeamColor;

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

    public String getWinner() {
        if (whiteScore.equals(blackScore)) {
            return "무승부";
        }
        if (whiteScore > blackScore) {
            return "White";
        }
        return "Black";
    }
}
