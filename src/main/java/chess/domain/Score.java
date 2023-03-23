package chess.domain;

import java.util.Objects;

public final class Score {

    private static final int MIN = 0;

    private final double score;

    public Score(double score) {
        this.score = score;
    }

    public static Score min() {
        return new Score(MIN);
    }

    public Score add(Score other) {
        return new Score(this.score + other.score);
    }

    public int compareTo(Score other) {
        return Double.compare(this.score, other.score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
