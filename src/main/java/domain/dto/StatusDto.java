package domain.dto;

import domain.piece.Color;
import domain.score.Score;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StatusDto implements MenuDto {
    Map<String, Double> result = new HashMap<>();

    public StatusDto(Map<Boolean, Score> pieces) {
        for (Boolean color : pieces.keySet()) {
            result.put(Color.findColorName(color), pieces.get(color).getScore());
        }
    }

    public Map<String, Double> getMenuDto() {
        return Collections.unmodifiableMap(result);
    }

    public static Map<String, Double> create(Map<Boolean, Score> piecesScore) {
        Map<String, Double> result = new HashMap<>();
        for (Boolean color : piecesScore.keySet()) {
            result.put(Color.findColorName(color), piecesScore.get(color).getScore());
        }
        return result;
    }
}
