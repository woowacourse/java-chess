package chess.dto;

import chess.domain.Color;
import java.util.HashMap;
import java.util.Map;

public class ScoreDto {
    private final Map<Color, Double> score;

    private ScoreDto(Map<Color, Double> score) {
        this.score = score;
    }

    public static ScoreDto from(Map<Color, Double> score) {
        return new ScoreDto(score);
    }

    public Map<Color, Double> getScore() {
        return new HashMap<>(score);
    }
}
