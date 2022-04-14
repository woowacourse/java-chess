package chess.dto;

import chess.domain.game.Color;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResultDto {
    private final List<ScoreDto> scores;
    private final Color winner;
    private final boolean isDraw;
    private final boolean isGameFinished;

    public ResultDto(Map<Color, Double> scores) {
        this.scores = scores.keySet()
                .stream()
                .map(color -> new ScoreDto(color, scores.get(color)))
                .collect(Collectors.toList());

        this.winner = decideWinner(scores);

        this.isDraw = isDraw(scores);

        this.isGameFinished = this.scores.stream()
                .map(ScoreDto::getScore)
                .anyMatch(score -> Double.compare(score, 0) == 0);
    }

    private Color decideWinner(Map<Color, Double> scores) {
        if (scores.get(Color.WHITE) > scores.get(Color.BLACK)) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    private boolean isDraw(Map<Color, Double> scores) {
        double whiteScore = scores.get(Color.WHITE);
        double blackScore = scores.get(Color.BLACK);
        return Double.compare(whiteScore, blackScore) == 0;
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }
}
