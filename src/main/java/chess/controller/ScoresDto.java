package chess.controller;

import java.util.Map;

public class ScoresDto {

    private String winner;
    private Map<String, Double> scores;

    public ScoresDto(String winner, Map<String, Double> scores) {
        this.winner = winner;
        this.scores = scores;
    }

    public String getWinner() {
        return winner;
    }

    public Map<String, Double> getScores() {
        return scores;
    }
}
