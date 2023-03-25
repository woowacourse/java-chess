package domain.piece;

public final class Score {
    private final double score;

    public Score(final double score) {
        validateScore(score);
        this.score = score;
    }

    private void validateScore(double score) {
        if (score >= 0) {
            return;
        }
        throw new IllegalArgumentException("점수는 음수가 될 수 없습니다.");
    }

    public double getScore() {
        return score;
    }
}
