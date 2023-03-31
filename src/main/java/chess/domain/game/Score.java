package chess.domain.game;

public class Score {
    
    private final double value;

    private Score(double score) {
        validate(score);
        this.value = score;
    }

    public static Score valueOf(double score) {
        return new Score(score);
    }

    private void validate(double score) {
        if (score < 0) {
            throw new IllegalArgumentException("점수는 음수일 수 없습니다.");
        }
    }

    public double getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Score score = (Score) o;

        return Double.compare(score.value, value) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }
}
