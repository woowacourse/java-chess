package chess.domain.game;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Result {
    private final List<Score> scores;
    private final Color winner;
    private final boolean isDraw;
    private final boolean isGameFinished;

    public Result(Map<Color, Double> scores) {
        this.scores = scores.keySet()
                .stream()
                .map(color -> new Score(color, scores.get(color)))
                .collect(Collectors.toList());

        this.winner = decideWinner(scores);

        this.isDraw = isDraw(scores);

        this.isGameFinished = this.scores.stream()
                .map(Score::getScore)
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

    public double getScoresOf(Color color) {
        Score targetScore = scores.stream().filter(score -> score.isSameColorWith(color))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("존재하지 않는 색상입니다."));
        return targetScore.getScore();
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }
}
