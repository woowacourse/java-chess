package chess.dto;

import chess.domain.Color;
import chess.domain.WinResult;
import java.util.Map;

public class StatusDto {

    private final Map<Color, Double> scores;
    private final WinResult result;

    public StatusDto(Map<Color, Double> scores) {
        this.scores = scores;
        double blackScore = scores.get(Color.BLACK);
        double whiteScore = scores.get(Color.WHITE);
        this.result = WinResult.of(blackScore, whiteScore);
    }

    public Map<Color, Double> getScores() {
        return scores;
    }

    public WinResult getResult() {
        return result;
    }
}
