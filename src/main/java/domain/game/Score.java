package domain.game;

public class Score {
    private final double number;

    public Score(double number) {
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    public Score add(Score scoreToAdd) {
        return new Score(this.number + scoreToAdd.number);
    }

    @Override
    public boolean equals(Object scoreToCompare) {
        return this.number == ((Score) scoreToCompare).number;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
