package chess.domain;

public final class Score {

    private static final Score init = new Score(0);

    private final double value;

    private Score(final double value) {
        this.value = value;
    }

    public static Score init() {
        return init;
    }

    public Score plus(final double value) {
        return new Score(this.value + value);
    }

    public double getValue() {
        return value;
    }
}
