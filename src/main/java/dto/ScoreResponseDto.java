package dto;

import java.util.Map;
import java.util.stream.Collectors;

import domain.piece.Camp;

public class ScoreResponseDto {
    private final Map<Camp, Double> score;

    public ScoreResponseDto(Map<Camp, Double> score) {
        this.score = score;
    }

    public Map<String, Double> getScore() {
        return score.entrySet().stream()
            .collect(Collectors.toMap(entry -> entry.getKey().name(), Map.Entry::getValue));
    }
}
