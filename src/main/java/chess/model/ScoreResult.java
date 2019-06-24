package chess.model;

import java.util.Objects;

public class ScoreResult {
    private double scoreOfWhite;
    private double scoreOfBlack;

    public ScoreResult(double scoreOfWhite, double scoreOfBlack) {
        this.scoreOfWhite = scoreOfWhite;
        this.scoreOfBlack = scoreOfBlack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreResult that = (ScoreResult) o;
        return Double.compare(that.scoreOfWhite, scoreOfWhite) == 0 &&
                Double.compare(that.scoreOfBlack, scoreOfBlack) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreOfWhite, scoreOfBlack);
    }
}
