package chess.domain.game.score;

public class Score {

    private final double value;

    private Score(double value) {
        validateNotNegative(value);
        this.value = value;
    }

    public static Score from(double value) {
        return new Score(value);
    }

    private void validateNotNegative(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("음수를 전달할 수 없습니다.");
        }
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

    @Override
    public String toString() {
        return "Score{" +
                "value=" + value +
                '}';
    }
}
