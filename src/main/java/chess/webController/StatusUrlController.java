package chess.webController;

import chess.service.ScoreCalculateService;

public class StatusUrlController {
    private ScoreCalculateService scoreCalculateService;

    public StatusUrlController() {
        this.scoreCalculateService = new ScoreCalculateService();
    }

    public String calculateScore() {
        return String.format("%s점수: %.1f", scoreCalculateService.getColor(),
                scoreCalculateService.calculateScore());
    }
}
