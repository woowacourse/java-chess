package chess.dto;

import chess.domain.ChessGame;

public class GameResultDTO {
    private final String result;
    private final String enemyName;
    private final String team;
    private final double myScore;
    private final double enemyScore;

    public GameResultDTO(String result, String enemyName, String team, double myScore, double enemyScore) {
        this.result = result;
        this.enemyName = enemyName;
        this.team = team;
        this.myScore = myScore;
        this.enemyScore = enemyScore;
    }

    public String getResult() {
        return result;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public String getTeam() {
        return team;
    }

    public double getMyScore() {
        return myScore;
    }

    public double getEnemyScore() {
        return enemyScore;
    }
}
