package chess.domain;

import java.util.Objects;

public class Score {

    private final int value;

    Score(int value) {
        this.value = value;
    }

    Score add(Score score) {
        return new Score(value + score.value);
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
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
