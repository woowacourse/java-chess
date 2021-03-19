package chess.domain;

public class Score {

    private final double value;

    private Score(double value) {
        this.value = value;
    }

    public static Score from(double value) {
        return new Score(value);
    }

    public Score addedScore(Score score) {
        return from(this.value + score.value);
    }

    public Score minusPawnCount(int pawnCount) {
        return Score.from(value - (0.5 * pawnCount));
    }

    public double value() {
        return value;
    }

    public boolean isBiggerThan(Score score) {
        return value > score.value;
    }
}
