package chess.dto;

import chess.model.piece.Color;
import chess.model.util.ScoreResult;
import java.util.HashMap;
import java.util.Map;

public class ScoreDto {

    private final String winner;
    private final Map<String, Double> score;

    public ScoreDto(String winner, Map<String, Double> score) {
        this.winner = winner;
        this.score = score;
    }

    public static ScoreDto of(ScoreResult scoreResult) {
        String winner = scoreResult.findWinnerName();
        Map<String, Double> score = new HashMap<>();
        for (Color color : scoreResult.keySet()) {
            score.put(color.name(), scoreResult.get(color));
        }
        return new ScoreDto(winner, score);
    }

    public String getWinner() {
        return winner;
    }

    public Map<String, Double> getScore() {
        return score;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String color : score.keySet()) {
            sb.append("\"").append(color).append("\" : ").append(score.get(color)).append(",");
        }
        sb.append("\"").append("winner").append("\" : ").append("\"").append(winner).append("\"");
        sb.append("}");
        return sb.toString();
    }
}
