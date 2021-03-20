package chess.domain.piece;

public enum Score {
    ZERO(0),
    ONE(1),
    THREE(3),
    TOW_FIVE(2.5),
    FIVE(5),
    NINE(9);

    private final double value;

    Score(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
