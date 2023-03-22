package chess.domain;


import java.util.Objects;

public class Score {

    private final double value;

    private Score(double value) {
        this.value = value;
    }

    public static Score from(double value) {
        return new Score(value);
    }

    public Score subtract(Score other) {
        return new Score(this.value - other.value);
    }

    public double getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
