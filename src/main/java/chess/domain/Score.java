package chess.domain;

public final class Score {

    private final double value;

    public Score(double value) {
        this.value = value;
    }

    public static Score from(double value) {
        return new Score(value);
    }
}
