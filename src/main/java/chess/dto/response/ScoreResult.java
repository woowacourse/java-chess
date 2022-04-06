package chess.dto.response;

import chess.piece.Color;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreResult {
    private final Map<String, Double> score;

    public ScoreResult(final Map<Color, Double> score) {
        this.score = toScore(score);
    }

    private Map<String, Double> toScore(final Map<Color, Double> score) {
        return score.keySet()
                .stream()
                .collect(Collectors.toMap(Enum::name, score::get));
    }

    public Map<String, Double> getScore() {
        return score;
    }
}
