package chess.domain.piece;

public class Score {

    private final double value;

    public Score(final double value) {
        this.value = value;
    }

    public double value() {
        return this.value;
    }

    public Score add(Score score) {
        return new Score(this.value + score.value);
    }

    public double getScore(){
        return this.value;
    }
}
