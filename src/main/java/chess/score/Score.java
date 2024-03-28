package chess.score;

public class Score {

    public static final Score ZERO = new Score(0);

    private final double score;

    private Score(double score) {
        validatePositiveScore(score);
        validateDecimalPart(score);
        this.score = score;
    }

    public static Score of(double score) {
        return new Score(score);
    }

    private void validatePositiveScore(double score) {
        if (score < 0) {
            throw new IllegalArgumentException("점수는 음수가 될 수 없습니다.");
        }
    }

    private void validateDecimalPart(double score) {
        if (isNotDivisibleWithHalf(score)) {
            throw new IllegalArgumentException("점수는 0.5의 배수여야 합니다.");
        }
    }

    private boolean hasDecimalPart() {
        return score % 1 != 0;
    }

    private boolean isNotDivisibleWithHalf(double score) {
        return score % 0.5 != 0;
    }

    public Score add(Score other) {
        return new Score(score + other.score);
    }

    public Score divideInHalf() {
        if (hasDecimalPart()) {
            throw new IllegalStateException("나눌 점수는 소수점 부분이 존재하지 않아야 합니다.");
        }
        return new Score(score / 2);
    }

    public Score multiplyBy(int multiplier) {
        return new Score(score * multiplier);
    }

    public double getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Score score = (Score) o;

        return Double.compare(this.score, score.score) == 0;
    }

    @Override
    public int hashCode() {
        final long temp = Double.doubleToLongBits(score);
        return (int) (temp ^ (temp >>> 32));
    }
}
