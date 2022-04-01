package chess.domain.piece.property;

import java.util.List;

public class Score {
    private final double score;

    public Score(double score) {
        this.score = score;
    }

    public static double computeScore(List<Score> scores) {
        return scores.stream()
            .mapToDouble(property -> property.score)
            .sum();
    }
}
