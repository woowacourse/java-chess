package chess.controller.dto.response;

import chess.domain.player.score.Score;
import chess.domain.player.score.Scores;

public class ScoresResponseDTO {
    private final Score blackPlayerScore;
    private final Score whitePlayerScore;

    public ScoresResponseDTO(Scores scores) {
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
