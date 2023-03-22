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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
