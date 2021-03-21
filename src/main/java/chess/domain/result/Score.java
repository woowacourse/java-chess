package chess.domain.result;

public class Score {
    private static final double MINIMUM_SCORE = 0.0;

    private final double score;

    public Score(double score) {
        validateScore(score);
        this.score = score;
    }

    private void validateScore(double score) {
        if (score < MINIMUM_SCORE) {
            throw new IllegalArgumentException("점수는 0 이상이어야 합니다.");
        }
    }

    public double getScore() {
        return score;
    }
}
