package chess.domain.piece.info;

public class Score {
    public static final Score ZERO = new Score(0);
    public static final double SUBTRACT_VALUE = 0.5;

    private final double value;

    public Score(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public double sum(Score anotherScore) {
        return value + anotherScore.value;
    }

    public double subtractedByMultipliedCount(int sameXPawnCount) {
        return value - (SUBTRACT_VALUE * sameXPawnCount);
    }
}
