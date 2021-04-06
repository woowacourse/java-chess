package chess.controller.dto;

import chess.domain.GameResult;

public class ScoreDTO {

    private double whiteTeamScore;
    private double blackTeamScore;

    public ScoreDTO(GameResult gameResult) {
        this.whiteTeamScore = gameResult.getWhiteTeamScore().value();
        this.blackTeamScore = gameResult.getBlackTeamScore().value();
    }

    public double getWhiteTeamScore() {
        return whiteTeamScore;
    }

    public double getBlackTeamScore() {
        return blackTeamScore;
    }
}
