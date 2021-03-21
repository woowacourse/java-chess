package domain.score;

import java.util.*;

public class Score {
    private final double value;
    private static final Map<Double, Score> CACHE = new WeakHashMap<>();

    public static final Score ZERO = Score.of(0);

    private Score(double value) {
        this.value = value;
    }

    public static Score of(double value) {
        return CACHE.computeIfAbsent(value, Score::new);
    }

    public Score add(Score score) {
        return Score.of(this.value + score.value);
    }

    public Score half() {
        return new Score(value / 2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Double.compare(score.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public double getScore() { //  test용
        return value;
    }
}
