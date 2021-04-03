package domain.score;

import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;

public class Score {
    private static final Map<Double, Score> CACHE = new WeakHashMap<>();
    private final double value;

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

    public double getValue() {
        return value;
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
}
