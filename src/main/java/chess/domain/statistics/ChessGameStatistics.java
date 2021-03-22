package chess.domain.statistics;

import chess.domain.piece.attribute.Color;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChessGameStatistics {
    private static final double DEFAULT_SCORE = 38;

    private final Map<Color, Double> colorsScore;
    private final MatchResult matchResult;

    public ChessGameStatistics(Map<Color, Double> colorsScore, MatchResult matchResult) {
        this.colorsScore = colorsScore;
        this.matchResult = matchResult;
    }

    public static ChessGameStatistics createNotStartGameResult(){
        Map<Color, Double> colorsScore = new HashMap<>();
        Color.getUserColors().stream()
                .forEach(color -> colorsScore.put(color, DEFAULT_SCORE));
        return new ChessGameStatistics(colorsScore, MatchResult.DRAW);
    }
    public Map<Color, Double> getColorsScore() {
        return Collections.unmodifiableMap(colorsScore);
    }

    public String getResultText(){
        return this.matchResult.getText();
    }
}
