package domain.game;

public class Score {
    private final double number;

    public Score(double number) {
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    public static Score add(Score firstScore, Score secondScore) {
        return new Score(firstScore.number + secondScore.number);
    }

    @Override
    public boolean equals(Object scoreToCompare) {
        if (scoreToCompare instanceof Score) {
            return this.number == ((Score) scoreToCompare).number;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
