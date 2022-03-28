package chess.controller;

import chess.model.Color;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class ScoreResult {

    private final Map<Color, Double> scoreResult = new LinkedHashMap<>();

    public void addResult(final Color color, final Double score) {
        scoreResult.put(color, score);
    }

    public String findWinnerName() {
        final double subtractedScore = Color.getPlayerColors().stream()
                .mapToDouble(scoreResult::get)
                .reduce((x, y) -> x - y)
                .orElseThrow(() -> new IllegalArgumentException("우승자를 찾을 수 없습니다."));
        return findWinner(subtractedScore);
    }

    public Set<Color> keySet() {
        return scoreResult.keySet();
    }

    public Double get(final Color color) {
        return scoreResult.get(color);
    }

    private String findWinner(final Double subtractedScore) {
        if (subtractedScore.equals(0.0)) {
            return "무승부";
        }
        if (subtractedScore < 0) {
            return "WHITE 승";
        }
        return "BLACK 승";
    }
}
