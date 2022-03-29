package chess.domain;

import chess.domain.player.Result;
import chess.domain.player.Score;

public class GameResult {

    private final String teamName;
    private final Score playerScore;
    private final Result playerResult;

    public GameResult(String teamName, Score playerScore, Result playerResult) {
        this.teamName = teamName;
        this.playerScore = playerScore;
        this.playerResult = playerResult;
    }

    public String getTeamName() {
        return teamName;
    }

    public double getPlayerScore() {
        return playerScore.getScore();
    }

    public String getPlayerResult() {
        return playerResult.getValue();
    }
}
