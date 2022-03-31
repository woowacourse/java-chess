package chess.dto;

import chess.domain.Color;
import chess.domain.Score;
import chess.domain.WinResult;
import java.util.Map;

public class StatusDto {

    private final Map<Color, Score> scores;
    private final WinResult winResult;

    public StatusDto(Map<Color, Score> scores) {
        this.scores = scores;
        Score blackScore = scores.get(Color.BLACK);
        Score whiteScore = scores.get(Color.WHITE);
        this.winResult = WinResult.of(blackScore, whiteScore);
    }

    public Map<Color, Score> getScoresByColor() {
        return scores;
    }

    public WinResult getWinResult() {
        return winResult;
    }
}
