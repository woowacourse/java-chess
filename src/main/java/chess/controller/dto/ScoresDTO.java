package chess.controller.dto;

import chess.domain.player.score.Score;
import chess.domain.player.score.Scores;

public class ScoresDTO {
    private final Score blackPlayerScore;
    private final Score whitePlayerScore;

    public ScoresDTO(Scores scores) {
        blackPlayerScore = scores.blackPlayerScore();
        whitePlayerScore = scores.whitePlayerScore();
    }

    public double blackPlayerScore() {
        return blackPlayerScore.getScore();
    }

    public double whitePlayerScore() {
        return whitePlayerScore.getScore();
    }
}
