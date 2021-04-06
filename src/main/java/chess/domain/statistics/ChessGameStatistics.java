package chess.domain.statistics;

import chess.domain.piece.Color;

import java.util.Collections;
import java.util.Map;

public class ChessGameStatistics {
    private final Map<Color, Double> colorsScore;
    private final MatchResult matchResult;

    public ChessGameStatistics(Map<Color, Double> colorsScore, MatchResult matchResult) {
        this.colorsScore = colorsScore;
        this.matchResult = matchResult;
    }

    public Map<Color, Double> getColorsScore() {
        return Collections.unmodifiableMap(colorsScore);
    }

    public String getResultText() {
        return this.matchResult.getText();
    }

    public MatchResult getMatchResult() {
        return this.matchResult;
    }
}
