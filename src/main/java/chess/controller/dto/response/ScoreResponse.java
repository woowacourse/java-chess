package chess.controller.dto.response;

import chess.domain.Color;
import java.util.Map.Entry;

public class ScoreResponse {

    private final String color;
    private final double score;

    private ScoreResponse(String color, double score) {
        this.color = color;
        this.score = score;
    }

    public static ScoreResponse from(Entry<Color, Double> score) {
        return new ScoreResponse(score.getKey().name(), score.getValue());
    }
}
