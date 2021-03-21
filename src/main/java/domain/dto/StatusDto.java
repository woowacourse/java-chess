package domain.dto;

import domain.piece.Color;
import domain.score.Score;

import java.util.HashMap;
import java.util.Map;

public class StatusDto {
    public static Map<String, Double> create(Map<Boolean, Score> piecesScore) {
        Map<String, Double> result = new HashMap<>();
        for (Boolean color : piecesScore.keySet()) {
            result.put(Color.findColorName(color), piecesScore.get(color).getScore());
        }
        return result;
    }
}
