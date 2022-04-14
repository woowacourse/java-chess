package chess.dto;

import chess.model.piece.Color;
import java.util.Map;

public class ScoreDto {

    private static final String CANNOT_FOUND_WINNER_ERROR_MESSAGE = "우승자를 찾을 수 없습니다.";
    private static final String DRAW_MESSAGE = "무승부";
    private static final String WHITE_WIN = "WHITE 승";
    private static final String BLACK_WIN = "BLACK 승";

    private final String winner;
    private final Map<String, Double> score;

    private ScoreDto(String winner, Map<String, Double> score) {
        this.winner = winner;
        this.score = score;
    }

    public static ScoreDto from(Map<String, Double> status) {
        String winner = findWinnerName(status);
        return new ScoreDto(winner, status);
    }

    private static String findWinnerName(Map<String, Double> status) {
        final double subtractedScore = Color.getPlayerColors().stream()
                .mapToDouble(color -> status.get(color.name()))
                .reduce((x, y) -> x - y)
                .orElseThrow(() -> new IllegalArgumentException(CANNOT_FOUND_WINNER_ERROR_MESSAGE));
        return findWinner(subtractedScore);
    }

    private static String findWinner(final Double subtractedScore) {
        if (subtractedScore.equals(0.0)) {
            return DRAW_MESSAGE;
        }
        if (subtractedScore < 0) {
            return BLACK_WIN;
        }
        return WHITE_WIN;
    }

    public String getWinner() {
        return winner;
    }

    public Map<String, Double> getScore() {
        return score;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (String color : score.keySet()) {
            stringBuilder.append("\"").append(color).append("\" : ").append(score.get(color)).append(",");
        }
        stringBuilder.append("\"").append("winner").append("\" : ").append("\"").append(winner).append("\"");
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
